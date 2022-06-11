package top.hzfq.common.model.entity;

/**
 * 数据字典项
 *
 * @author huzhifengqing@qq.com
 * @since 2022/2/26 10:49
 */
public class BaseDictItem extends BaseInfo {

    private Integer dictItemId;
    private Integer parentDictItemId;
    private Integer dictId;
    private String itemName;
    private String itemValue;
    private String itemDesc;
    private Integer itemOrder; //排序
    private Boolean itemStatus; //状态
    private Boolean defaultItem; //默认项

}
