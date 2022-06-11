package top.hzfq.flow.flowable.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.BpmnAutoLayout;
import org.flowable.bpmn.converter.BpmnXMLConverter;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.Process;
import org.flowable.editor.language.json.converter.BpmnJsonConverter;
import org.flowable.ui.modeler.domain.AbstractModel;
import org.flowable.ui.modeler.domain.Model;
import org.flowable.ui.modeler.model.ModelKeyRepresentation;
import org.flowable.ui.modeler.serviceapi.ModelService;
import org.flowable.validation.ProcessValidator;
import org.flowable.validation.ValidationError;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.hzfq.flow.flowable.service.FlowableModelService;
import top.hzfq.flow.flowable.service.FlowableRepositoryService;

import javax.annotation.Resource;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author huzhifengqing@qq.com
 * @since 2022/2/27 20:49
 */
@Service
public class FlowableModelServiceImpl implements FlowableModelService {

    @Resource
    private FlowableRepositoryService repositoryService;
    @Resource
    private ModelService modelService;
    @Resource
    private BpmnXMLConverter bpmnXMLConverter;
    @Resource
    private BpmnJsonConverter bpmnJsonConverter;
    @Resource
    private ProcessValidator processValidator;
    @Resource
    private XMLInputFactory xmlInputFactory;

    @Override
    public void upload(MultipartFile modelFile, String $username) {
        String modelFileName = modelFile.getOriginalFilename();
        //model文件后缀
        if (StringUtils.endsWithAny(modelFileName, ".bpmn", ".bpmn20.xml")) {
            try {
                createModel(modelFile.getInputStream(), $username);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deploy(String modelId) {
        Model model = modelService.getModel(modelId);
        BpmnModel bpmnModel = modelService.getBpmnModel(model);
        repositoryService.deploy(model, bpmnModel);
    }

    @Override
    public Model getModel(String modelId) {
        return modelService.getModel(modelId);
    }

    @Override
    public byte[] getXmlBpmnModel(String modelId) {
        return new byte[0];
    }

    @Override
    public byte[] getPngBpmnModel(String modelId) {
        Model model = modelService.getModel(modelId);
        if (Model.MODEL_TYPE_BPMN != model.getModelType()) {
            throw new RuntimeException("模型不是BPMN类型：" + modelId);
        }
        BpmnModel bpmnModel = modelService.getBpmnModel(model);
        return new byte[0];
    }


    /**
     * 创建model
     */
    private void createModel(InputStream modelIS, String $username) {
        BpmnModel bpmnModel = parseBpmnModel(modelIS);
        List<ValidationError> validationErrors = processValidator.validate(bpmnModel);
        if (CollectionUtils.isNotEmpty(validationErrors)) {
            //验证失败
            return;
        }
        if (CollectionUtils.isEmpty(bpmnModel.getProcesses())) {
            //没有流程定义
            return;
        }
        if (bpmnModel.getLocationMap().size() == 0) {
            //没有布局信息时自动布局
            new BpmnAutoLayout(bpmnModel).execute();
        }

        Process mainProcess = bpmnModel.getMainProcess();
        String processId = mainProcess.getId();
        String processName = mainProcess.getName();
        if (StringUtils.isBlank(processName)) {
            processName = processId;
        }
        ModelKeyRepresentation modelKeyRepresentation =
                modelService.validateModelKey(null, AbstractModel.MODEL_TYPE_BPMN, processId);
        if (modelKeyRepresentation.isKeyAlreadyExists()) {
            //模型已经存在
            return;
        }
        Model model = new Model();
        model.setKey(processId);
        model.setName(processName);
        model.setModelType(AbstractModel.MODEL_TYPE_BPMN);
        model.setDescription(mainProcess.getDocumentation());
        model.setModelEditorJson(bpmnJsonConverter.convertToJson(bpmnModel).toString());
        modelService.createModel(model, $username);
    }

    /**
     * 从model流中解析BPMN模型对象
     */
    private BpmnModel parseBpmnModel(InputStream modelIS) {
        try {
            return bpmnXMLConverter.convertToBpmnModel(xmlInputFactory.createXMLStreamReader(modelIS,
                    StandardCharsets.UTF_8.name()));
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
    }
}
