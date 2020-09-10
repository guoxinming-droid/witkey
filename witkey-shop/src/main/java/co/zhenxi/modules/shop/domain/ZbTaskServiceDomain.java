package co.zhenxi.modules.shop.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @Author: Jia Hao Hao
 * @Date: 2020-09-02 15:30
 * @Description: ZbTaskService
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("zb_task_service")
public class ZbTaskServiceDomain {
    @TableId
    private long id;
    /** 任务ID */
    private long taskId;
    /** 服务Id */
    private Integer serviceId;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}
