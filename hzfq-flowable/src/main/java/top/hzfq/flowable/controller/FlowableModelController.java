package top.hzfq.flowable.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.hzfq.flowable.model.request.ModelDTO;
import top.hzfq.flowable.service.FlowableModelService;
import top.hzfq.flowable.util.FlowableConstant;

import javax.annotation.Resource;

/**
 * @author huzhifengqing@qq.com
 * @since 2022/2/28 22:17
 */
@RequestMapping("/model")
@RestController
public class FlowableModelController {

    @Resource
    private FlowableModelService modelService;

    /**
     * 新增模型
     */
    @PostMapping("/add")
    public void add(ModelDTO model) {

    }

    /**
     * 上传模型文件
     */
    @PostMapping("/upload")
    public void upload(MultipartFile modelFile, String $username) {
        $username = FlowableConstant.createBy;
        modelService.upload(modelFile, $username);
    }

    /**
     * 模型升版
     */
    @PostMapping("/upgrade")
    public void upgrade(MultipartFile modelFile, String $username) {

    }

    /**
     * 部署模型
     */
    @PostMapping("/deploy")
    public void deploy(String modelId) {
        modelService.deploy(modelId);
    }

    /**
     * 显示XML格式模型
     */
    @PostMapping("/xml")
    public void xml(String modelId) {
    }

    /**
     * 显示PNG格式模型
     */
    @PostMapping("/png")
    public void png(String modelId) {

    }
}
