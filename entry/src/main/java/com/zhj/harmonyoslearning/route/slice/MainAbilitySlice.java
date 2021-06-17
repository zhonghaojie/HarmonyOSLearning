package com.zhj.harmonyoslearning.route.slice;

import com.zhj.harmonyoslearning.ResourceTable;
import com.zhj.harmonyoslearning.route.ability.SecondAbility;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.colors.RgbColor;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.element.ShapeElement;
import ohos.agp.utils.Color;
import ohos.agp.utils.TextAlignment;
import ohos.hiviewdfx.HiLog;

import java.util.HashSet;
import java.util.Set;

import static com.zhj.harmonyoslearning.route.utils.Const.*;

public class MainAbilitySlice extends AbilitySlice implements Component.ClickedListener {


    public static final String ABILITY_SECOND_ACTION = "com.zhj.harmonyoslearning.ACTION";
    public static final String ABILITY_SECOND_ENTITY = "com.zhj.harmonyoslearning.ENTITY";

    Button explicitButton;
    Button javaButton;
    Button implicitButton;
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        createComponentInJavaCode();
        explicitButton = (Button) findComponentById(ResourceTable.Id_btn_explicit_startup);
        explicitButton.setClickedListener(this);
        implicitButton = (Button) findComponentById(ResourceTable.Id_btn_implicit_startup);
        implicitButton.setClickedListener(this);
        HiLog.debug(TAG, "onStart");
    }


    /**
     * 在Java代码中添加组件
     */
    private void createComponentInJavaCode() {
        DirectionalLayout layout = (DirectionalLayout) findComponentById(ResourceTable.Id_root);

        //region 设置容器宽高 ，两种方式都可以
//        DirectionalLayout.LayoutConfig layoutConfig = new DirectionalLayout.LayoutConfig(ComponentContainer.LayoutConfig.MATCH_PARENT, ComponentContainer.LayoutConfig.MATCH_PARENT);
//        layout.setLayoutConfig(layoutConfig);

        layout.setWidth(ComponentContainer.LayoutConfig.MATCH_PARENT);
        layout.setHeight(ComponentContainer.LayoutConfig.MATCH_PARENT);
        //endregion

        ShapeElement shapeElement = new ShapeElement();
        shapeElement.setRgbColor(new RgbColor(143, 188, 143));

        layout.setBackground(shapeElement);

        layout.setOrientation(Component.VERTICAL);
        //将组件添加到布局里
        javaButton = createComponent();
        layout.addComponent(javaButton);
    }

    /**
     * 创建组件
     */
    private Button createComponent() {
        //region 创建组件
        Button button = new Button(getContext());
        //region 设置控件的宽高、margin等，两种方式都可以
        /*
        DirectionalLayout.LayoutConfig buttonLayoutConfig = new DirectionalLayout.LayoutConfig(ComponentContainer.LayoutConfig.MATCH_CONTENT, ComponentContainer.LayoutConfig.MATCH_CONTENT);
        buttonLayoutConfig.setMargins(30, 10, 30, 10);
        buttonLayoutConfig.alignment = LayoutAlignment.CENTER;
        button.setLayoutConfig(buttonLayoutConfig);
        */

        button.setWidth(DirectionalLayout.LayoutConfig.MATCH_PARENT);
        button.setHeight(DirectionalLayout.LayoutConfig.MATCH_CONTENT);
        button.setMarginsLeftAndRight(20, 20);
        button.setMarginsTopAndBottom(40, 40);
        //endregion
        //设置id
        button.setId(100);
        button.setTextAlignment(TextAlignment.START | TextAlignment.VERTICAL_CENTER);
        //控制组件在父容器中的位置

        //设置padding
        button.setPadding(50, 20, 50, 20);

        //设置背景颜色
        ShapeElement element = new ShapeElement();
        element.setCornerRadius(20f);
        element.setRgbColor(new RgbColor(255, 99, 71));
        button.setBackground(element);

        //设置文本
        button.setText(ResourceTable.String_javaCodeButton);
//        button.setText("javaCodeButton");
        button.setTextColor(Color.WHITE);

        //设置文本大小
        button.setTextSize(50);

        button.setClickedListener(this);
        return button;
    }

    @Override
    public void onClick(Component component) {
        switch (component.getId()) {
            case ResourceTable.Id_btn_implicit_startup:
                implicitStartup();
                break;
            case ResourceTable.Id_btn_explicit_startup:
                explicitStartup();
                break;
            case 100:
                present(new MainAbilitySlice2(), new Intent());
                break;
        }
    }

    /**
     * 显示启动
     */
    private void explicitStartup(){
        Intent intent = new Intent();
        Operation operation = new Intent.OperationBuilder()
                .withDeviceId("")//要跳转到的设备ID，空字符串就是本设备
                .withBundleName("com.zhj.harmonyoslearning")//包名
                .withAbilityName(SecondAbility.class)
                .build();
        intent.setOperation(operation);
        intent.setParam("param","显示启动");
        startAbilityForResult(intent,ABILITY_SECOND_REQUEST_CODE);

    }

    /**
     * 隐式启动
     */
    private void implicitStartup(){
        Intent intent = new Intent();
        Set<String> entries = new HashSet<>();
        entries.add(ABILITY_SECOND_ENTITY);
        Operation operation = new Intent.OperationBuilder().withDeviceId("")
                .withAction(ABILITY_SECOND_ACTION)
                .withEntities(entries)
                .build();
        intent.setOperation(operation);
        intent.setParam("param","隐式启动");
        startAbilityForResult(intent,ABILITY_SECOND_REQUEST_CODE);
    }

    @Override
    protected void onAbilityResult(int requestCode, int resultCode, Intent resultData) {
        super.onAbilityResult(requestCode, resultCode, resultData);
        if(requestCode == ABILITY_SECOND_REQUEST_CODE && resultCode == ABILITY_SECOND_RESULT_CODE){
            String result = resultData.getStringParam(PARAM_SECOND_ABILITY_RESULT);
            javaButton.setText(result);
        }
    }
}
