package top.hzfq.flow.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author huzhifengqing@qq.com
 * @since 2022/8/21
 */
public interface FlowModelService {

    void uploadModel(MultipartFile modelFile, String username);

    void deploy(String modelId);

}
