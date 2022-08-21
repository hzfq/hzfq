package top.hzfq.flow.service.impl;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.BpmnAutoLayout;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.Process;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.impl.persistence.entity.ModelEntity;
import org.flowable.engine.impl.persistence.entity.ModelEntityImpl;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.Model;
import org.flowable.validation.ValidationError;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.hzfq.flow.util.FlowUtil;
import top.hzfq.flow.service.FlowModelService;

import javax.annotation.Resource;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author huzhifengqing@qq.com
 * @since 2022/8/21
 */
@Service
public class FlowModelServiceImpl implements FlowModelService {

    @Resource
    private RepositoryService repositoryService;

    @Override
    public void uploadModel(MultipartFile modelFile, String username) {
        BpmnModel bpmnModel = null;
        try {
            bpmnModel = FlowUtil.extractBpmnModel(modelFile.getInputStream());
        } catch (XMLStreamException | IOException e) {
            throw new RuntimeException(e);
        }

        List<ValidationError> validationErrors = FlowUtil.validateBpmnModel(bpmnModel);
        if (CollectionUtils.isNotEmpty(validationErrors)) {
            throw new RuntimeException(validationErrors.stream().map(ValidationError::getProblem).collect(Collectors.joining("; ")));
        }
        if (CollectionUtils.isEmpty(bpmnModel.getProcesses())) {
            throw new RuntimeException("No process found in definition " + modelFile.getOriginalFilename());
        }
        if (bpmnModel.getLocationMap().size() == 0) {
            BpmnAutoLayout bpmnLayout = new BpmnAutoLayout(bpmnModel);
            bpmnLayout.execute();
        }

        Process process = bpmnModel.getMainProcess();
        String key = process.getId();
        String name = key;
        if (StringUtils.isNotEmpty(process.getName())) {
            name = process.getName();
        }


        Model model = getModel(key);
        if (null != model) {
            throw new RuntimeException("key exist: " + key);
        }

        model = new ModelEntityImpl();
        model.setKey(key);
        model.setName(name);
        repositoryService.saveModel(model);

        model = getModel(key);

        repositoryService.addModelEditorSource(model.getId(), FlowUtil.convertToXml(bpmnModel));

    }

    @Override
    public void deploy(String modelId) {

        Model model = repositoryService.getModel(modelId);

        BpmnModel bpmnModel = FlowUtil.convertXmlToBpmnModel(repositoryService.getModelEditorSource(modelId));

        repositoryService.createDeployment()
                .addBpmnModel(model.getName(), bpmnModel)
                .key(model.getKey())
                .name(model.getName())
                .deploy();
    }


    public Model getModel(String key) {
        return repositoryService.createModelQuery().modelKey(key).singleResult();
    }

    public Model getModel(String name, String category) {
        return repositoryService.createModelQuery().modelCategory(category).modelName(name).singleResult();
    }

    public byte[] getModelXml(String modelId) {
        return repositoryService.getModelEditorSource(modelId);
    }

    public byte[] getModelJson(String modelId) {
        return repositoryService.getModelEditorSourceExtra(modelId);
    }
}
