package com.hotdog.saas.infra.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class CanalOptionLogConsumer extends AbstractKafkaConsumer{

    @KafkaListener(topics = "canal")
    public void listen(String message, Acknowledgment acknowledgment) {
        try {
//            super.d
            System.out.println("Received message: " + message);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            acknowledgment.acknowledge();
        }
    }

        /*
    INSERT，UPDATE
     */

    /*
    {
	"data": [{
		"id": "2",
		"name": "测试的",
		"contact_name": "热狗",
		"contract_phone": "18946703560",
		"contract_email": "wudonghe1996@gmail.com",
		"app_id": "10000002",
		"app_secret": "ncfm3frlOdPTL7vu1r3UaVHQLaHdzewwj69hj+qo3y8C3sVlLAIV+Tr2nwCfD21Y",
		"expire_time": "2099-12-31 00:00:00",
		"status": "0",
		"deleted": "0",
		"creator": "hotdog",
		"create_time": "2024-12-18 16:18:08",
		"updater": "hotdog",
		"update_time": "2024-12-18 18:50:45"
	}],
	"database": "hotdog-saas",
	"es": 1734519045000,
	"gtid": "",
	"id": 8,
	"isDdl": false,
	"mysqlType": {
		"id": "bigint",
		"name": "varchar(100)",
		"contact_name": "varchar(50)",
		"contract_phone": "varchar(20)",
		"contract_email": "varchar(100)",
		"app_id": "varchar(50)",
		"app_secret": "varchar(100)",
		"expire_time": "datetime",
		"status": "tinyint(1)",
		"deleted": "tinyint(1)",
		"creator": "varchar(30)",
		"create_time": "datetime",
		"updater": "varchar(30)",
		"update_time": "datetime"
	},
	"old": [{
		"name": "测试的1",
		"update_time": "2024-12-18 16:31:29"
	}],
	"pkNames": ["id"],
	"sql": "",
	"sqlType": {
		"id": -5,
		"name": 12,
		"contact_name": 12,
		"contract_phone": 12,
		"contract_email": 12,
		"app_id": 12,
		"app_secret": 12,
		"expire_time": 93,
		"status": -6,
		"deleted": -6,
		"creator": 12,
		"create_time": 93,
		"updater": 12,
		"update_time": 93
	},
	"table": "t_tenant",
	"ts": 1734519045399,
	"type": "UPDATE"
}
     */
}
