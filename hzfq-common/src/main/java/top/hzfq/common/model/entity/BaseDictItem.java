package top.hzfq.common.model.entity;

/**
 * 数据字典项
 *
 * @author huzhifengqing@qq.com
 * @since 2022/2/26 10:49
 */
public class BaseDictItem extends BaseInfo {

    private Long dictItemId;
    private Long dictId;
    private String itemName;
    private String itemValue;
    private String itemDesc;
    private Integer itemOrder; //序号
    private Boolean itemStatus; //状态

}
