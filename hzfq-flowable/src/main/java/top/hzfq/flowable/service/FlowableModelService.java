package top.hzfq.flowable.service;

import org.flowable.ui.modeler.domain.Model;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author huzhifengqing@qq.com
 * @since 2022/2/27 20:49
 */
public interface FlowableModelService {

    void upload(MultipartFile modelFile, String $username);

    void deploy(String modelId);

    Model getModel(String modelId);

    byte[] getXmlBpmnModel(String modelId);

    byte[] getPngBpmnModel(String modelId);
}
