package top.hzfq.flow.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.hzfq.flow.service.FlowModelService;

import javax.annotation.Resource;

/**
 * @author huzhifengqing@qq.com
 * @since 2022/8/21
 */
@Tag(name = "模型管理")
@RequestMapping(value = "model", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class FlowModelController {

    @Resource
    private FlowModelService flowModelService;

    @Operation(summary = "上传模型")
    @PostMapping(value = "upload")
    public void upload(MultipartFile modelFile) {
        flowModelService.uploadModel(modelFile, "");
    }


    @Operation(summary = "部署流程")
    @PostMapping(value = "deploy")
    public void deploy(String modelId) {
        flowModelService.deploy(modelId);
    }
}
