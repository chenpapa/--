# 短剧管理平台（Spring Boot）

一个轻量可运行的 Spring Boot 短剧管理平台后端示例，支持：

- 短剧 CRUD
- 按分类筛选短剧
- 剧集新增与列表
- 模拟播放量增长
- 运营看板统计

## 运行

```bash
mvn spring-boot:run
```

## 核心接口

- `GET /api/dramas`：查询短剧（可选参数 `category`）
- `POST /api/dramas`：创建短剧
- `PUT /api/dramas/{id}`：更新短剧
- `DELETE /api/dramas/{id}`：删除短剧
- `GET /api/dramas/{dramaId}/episodes`：查询剧集
- `POST /api/dramas/{dramaId}/episodes`：新增剧集
- `POST /api/dramas/{dramaId}/episodes/{episodeId}/play`：播放一次剧集
- `GET /api/dashboard/overview`：看板统计

> 默认使用 H2 内存数据库，并自动注入一条短剧样例数据。
