package com.frank.mybatis.plugin;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

/**
 * @ClassName com.frank.mybatis.plugin.LombokPlugin
 * @Description 整合Lombok
 */
public class SwaggertPlugin extends PluginAdapter {

    private FullyQualifiedJavaType dataAnnotation;

    public SwaggertPlugin() {
        dataAnnotation = new FullyQualifiedJavaType("io.swagger.annotations.ApiModelProperty");
    }

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    /**
     * 拦截 普通字段
     *
     * @param topLevelClass
     * @param introspectedTable
     * @return
     */
    @Override
    public boolean modelFieldGenerated(Field field,
                                       TopLevelClass topLevelClass,
                                       IntrospectedColumn introspectedColumn,
                                       IntrospectedTable introspectedTable,
                                       ModelClassType modelClassType) {
        addDataAnnotation(field,topLevelClass);
        return true;
    }

    /**
     * 拦截 主键
     *
     * @param topLevelClass
     * @param introspectedTable
     * @return
     */
//    @Override
//    public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass,
//                                                 IntrospectedTable introspectedTable) {
//        addDataAnnotation(topLevelClass);
//        return true;
//    }

    /**
     * 拦截 blob 类型字段
     *
     * @param topLevelClass
     * @param introspectedTable
     * @return
     */
//    @Override
//    public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass topLevelClass,
//                                                      IntrospectedTable introspectedTable) {
//        addDataAnnotation(topLevelClass);
//        return true;
//    }

    /**
     * Prevents all getters from being generated.
     * See SimpleModelGenerator
     *
     * @param method
     * @param topLevelClass
     * @param introspectedColumn
     * @param introspectedTable
     * @param modelClassType
     * @return
     */
    @Override
    public boolean modelGetterMethodGenerated(Method method,
                                              TopLevelClass topLevelClass,
                                              IntrospectedColumn introspectedColumn,
                                              IntrospectedTable introspectedTable,
                                              ModelClassType modelClassType) {
        return false;
    }

    /**
     * Prevents all setters from being generated
     * See SimpleModelGenerator
     *
     * @param method
     * @param topLevelClass
     * @param introspectedColumn
     * @param introspectedTable
     * @param modelClassType
     * @return
     */
    @Override
    public boolean modelSetterMethodGenerated(Method method,
                                              TopLevelClass topLevelClass,
                                              IntrospectedColumn introspectedColumn,
                                              IntrospectedTable introspectedTable,
                                              ModelClassType modelClassType) {
        return false;
    }

    /**
     * Adds the @Data lombok import and annotation to the class
     *
     * @param topLevelClass
     */
    protected void addDataAnnotation(Field field,TopLevelClass topLevelClass) {
        topLevelClass.addImportedType(dataAnnotation);
        field.addAnnotation("@ApiModelProperty(name=\""+field.getName()+"\")");
    }

}
