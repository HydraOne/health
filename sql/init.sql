DROP TABLE IF EXISTS ROLE;
CREATE TABLE ROLE (
                      ROLE_ID              VARCHAR(32)      NOT NULL    COMMENT '角色ID',
                      ROLE_NAME            VARCHAR(30)     NOT NULL                   COMMENT '角色名称',
                      ROLE_KEY             VARCHAR(100)    NOT NULL                   COMMENT '角色权限字符串',
                      ROLE_SORT            INT(4)          NOT NULL                   COMMENT '显示顺序',
                      DATA_SCOPE           CHAR(1)         DEFAULT '1'                COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
                      MENU_CHECK_STRICTLY  TINYINT(1)      DEFAULT 1                  COMMENT '菜单树选择项是否关联显示',
                      DEPT_CHECK_STRICTLY  TINYINT(1)      DEFAULT 1                  COMMENT '部门树选择项是否关联显示',
                      STATUS               CHAR(1)         NOT NULL                   COMMENT '角色状态（0正常 1停用）',
                      DEL_FLAG             CHAR(1)         DEFAULT '0'                COMMENT '删除标志（0代表存在 2代表删除）',
                      CREATE_BY            VARCHAR(64)     DEFAULT ''                 COMMENT '创建者',
                      CREATE_TIME          DATETIME                                   COMMENT '创建时间',
                      UPDATE_BY            VARCHAR(64)     DEFAULT ''                 COMMENT '更新者',
                      UPDATE_TIME          DATETIME                                   COMMENT '更新时间',
                      REMARK               VARCHAR(500)    DEFAULT NULL               COMMENT '备注',
                      PRIMARY KEY (ROLE_ID)
) COMMENT = '角色信息表';


DROP TABLE IF EXISTS USER_ROLE;
CREATE TABLE USER_ROLE (
                           USER_ID   VARCHAR(32) NOT NULL COMMENT '用户ID',
                           ROLE_ID   VARCHAR(32) NOT NULL COMMENT '角色ID',
                           PRIMARY KEY(USER_ID, ROLE_ID)
) COMMENT = '用户和角色关联表';