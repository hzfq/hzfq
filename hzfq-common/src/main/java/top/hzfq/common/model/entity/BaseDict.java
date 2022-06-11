package top.hzfq.common.model.entity;

/**
 * 数据字典
 * <p>
 * 唯一性：字典名+字典模块、字典编码+字典模块
 *
 * @author huzhifengqing@qq.com
 * @since 2022/2/26 10:49
 */
public class BaseDict extends BaseInfo {

    private Integer dictId;
    private Integer parentDictId;
    private String dictName; //字典名
    private String dictCode; //字典编码
    private String dictDesc;
    private String dictModule; //字典模块
    private String dictCategory;
    private Boolean dictStatus;

}
