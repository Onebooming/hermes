package com.onebooming.canal.listener;
import com.alibaba.fastjson.JSON;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.onebooming.content.feign.ContentFeign;
import com.onebooming.content.pojo.Content;
import com.xpand.starter.canal.annotation.*;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2020-02-20 12:02
 * @ApiNote
 */
@CanalEventListener
public class CanalDataEventListener {
    @Autowired
    private ContentFeign contentFeign;
    //字符串
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //自定义数据库的 操作来监听
    //destination = "example"
    @ListenPoint(destination = "example",
            schema = "changgou_content",
            table = {"tb_content", "tb_content_category"},
            eventType = {
                    CanalEntry.EventType.UPDATE,
                    CanalEntry.EventType.DELETE,
                    CanalEntry.EventType.INSERT})
    public void onEventCustomUpdate(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        //1.获取列名 为category_id的值
        String categoryId = getColumnValue(eventType, rowData);
        //2.调用feign 获取该分类下的所有的广告集合
        Result<List<Content>> categoryresut = contentFeign.findByCategory(Long.valueOf(categoryId));
        List<Content> data = categoryresut.getData();
        //3.使用redisTemplate存储到redis中
        stringRedisTemplate.boundValueOps("content_" + categoryId).set(JSON.toJSONString(data));
    }

    private String getColumnValue(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        String categoryId = "";
        //判断 如果是删除  则获取beforlist
        if (eventType == CanalEntry.EventType.DELETE) {
            for (CanalEntry.Column column : rowData.getBeforeColumnsList()) {
                if (column.getName().equalsIgnoreCase("category_id")) {
                    categoryId = column.getValue();
                    return categoryId;
                }
            }
        } else {
            //判断 如果是添加 或者是更新 获取afterlist
            for (CanalEntry.Column column : rowData.getAfterColumnsList()) {
                if (column.getName().equalsIgnoreCase("category_id")) {
                    categoryId = column.getValue();
                    return categoryId;
                }
            }
        }
        return categoryId;
    }
}

//@CanalEventListener
//public class CanalDataEventListener {
//
//    /***
//     * 增加数据监听 ：只有增加后的数据
//     * rowData.getAfterColumnsList()：获得操作后的数据：增加、修改
//     * getBeforeColumnsList():获得操作前的数据：修改、删除
//     * @param eventType：当前操作的类型
//     * @param rowData：发生变更的一行数据
//     */
//    @InsertListenPoint
//    public void onEventInsert(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
//        rowData.getAfterColumnsList().forEach((c) -> System.out.println("By--增加成功: " + c.getName() + " ::   " + c.getValue()));
//    }
//
//    /***
//     * 修改数据监听
//     * @param rowData
//     */
//    @UpdateListenPoint
//    public void onEventUpdate(CanalEntry.RowData rowData) {
//        System.out.println("UpdateListenPoint");
//        rowData.getBeforeColumnsList().forEach((c) -> System.out.println("By--修改前: " + c.getName() + " ::   " + c.getValue()));
//        rowData.getAfterColumnsList().forEach((c) -> System.out.println("By--修改后: " + c.getName() + " ::   " + c.getValue()));
//    }
//
//    /***
//     * 删除数据监听
//     * @param eventType
//     */
//    @DeleteListenPoint
//    public void onEventDelete(CanalEntry.EventType eventType,CanalEntry.RowData rowData) {
//        System.out.println("DeleteListenPoint");
//        rowData.getBeforeColumnsList().forEach((c) -> System.out.println("By--删除成功: " + c.getName() + " ::   " + c.getValue()));
//    }
//
//    /***
//     * 自定义数据修改监听
//     * 注解：@ListenPoint
//     *      destination---目标地址
//     *      schema---指定监听的数据库
//     *      table--指定要监听的目标数据表
//     *      eventType---监听类型：UPDATE修改，DELETE删除
//     * @param eventType
//     * @param rowData
//     */
//    @ListenPoint(destination = "example", schema = "changgou_content", table = {"tb_content_category", "tb_content"}, eventType = {CanalEntry.EventType.UPDATE,CanalEntry.EventType.DELETE})
//    public void onEventCustomUpdate(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
//        System.err.println("DeleteListenPoint");
//        rowData.getAfterColumnsList().forEach((c) -> System.out.println("By--Annotation: " + c.getName() + " ::   " + c.getValue()));
//    }
//}
