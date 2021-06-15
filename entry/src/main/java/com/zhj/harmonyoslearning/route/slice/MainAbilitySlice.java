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
import ohos.hiviewdfx.HiLogLabel;

public class MainAbilitySlice extends AbilitySlice implements Component.ClickedListener {
    static final HiLogLabel TAG = new HiLogLabel(HiLog.DEBUG, 1, "生命周期");

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        createComponentInJavaCode();
        Button xmlButton = (Button) findComponentById(ResourceTable.Id_btn_to_slice2);
        xmlButton.setClickedListener(this);
        HiLog.debug(TAG, "onStart");
    }

    @Override
    public void onActive() {
        super.onActive();
        HiLog.debug(TAG, "onActive");
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
        HiLog.debug(TAG, "onForeground");
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        HiLog.debug(TAG, "onInactive");
    }

    @Override
    protected void onBackground() {
        super.onBackground();
        HiLog.debug(TAG, "onBackground");
    }

    @Override
    protected void onStop() {
        super.onStop();
        HiLog.debug(TAG, "onStop");
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
        layout.addComponent(createComponent());
    }

    /**
     * 创建组件
     *
     * @return
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
            case ResourceTable.Id_btn_to_slice2:
                present(new MainAbilitySlice2(), new Intent());
                break;
            case 100:
                Intent intent = new Intent();
                Operation operation = new Intent.OperationBuilder()
                        .withDeviceId("")//要跳转到的设备ID，空字符串就是本设备
                        .withBundleName("com.zhj.harmonyoslearning")//包名
                        .withAbilityName(SecondAbility.class)
                        .build();
                intent.setOperation(operation);
                startAbility(intent);
                break;
        }
    }
}
