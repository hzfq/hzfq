package top.hzfq.common.model.entity;

/**
 * 数据字典
 *
 * @author huzhifengqing@qq.com
 * @since 2022/2/26 10:49
 */
public class BaseDict extends BaseInfo {

    private Long dictId;
    private String dictName;
    private String dictDesc;
    private String dictModule;
    private String dictCategory;
    private Boolean dictStatus;
    private Long parentDictId;

}
