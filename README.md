# EasyIndicator

一款简单的tab指示器，带平移动画效果，代码简单可配置各种tab切换样式，用得着的朋友希望点个star

使用方法：

```
      app:indicator_bottom_height="" 底部指示器高度
      app:indicator_bottom_line_color="" 底部线条颜色
      app:indicator_bottom_line_height="" 底部线条高度
      app:indicator_width="" tab宽度
      app:indicator_height="" tab高
      app:indicator_isBottom_line="" 是否显示底部线条
      app:indicator_line_color="" 底部指示器颜色     
      app:indicator_normal_color="" tab选中颜色      
      app:indicator_selected_color="" tab未选中颜色    
      app:indicator_textSize="" tab字体大小     
      app:indicator_vertical_line_w="" 垂直分割线条宽度     
      app:indicator_vertical_line_color="" 垂直线条颜色   
      app:indicator_vertical_line_h=""  垂直线条高度
      app:indicator_select_textSize="" 选中字体放大效果
```
      
```      
<com.luck.indicator.EasyIndicator
        android:id="@+id/easy_indicator"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:indicator_bottom_height="3dp"
        app:indicator_bottom_line_color="@color/line_color"
        app:indicator_bottom_line_height="1dp"
        app:indicator_height="42dp"
	app:indicator_select_textSize="14sp"
        app:indicator_isBottom_line="true"
        app:indicator_line_color="@color/app_color"
        app:indicator_normal_color="@color/color_70"
        app:indicator_selected_color="@color/app_color"
        app:indicator_textSize="14sp"
        app:indicator_vertical_line_w="1dp"
        app:indicator_vertical_line_color="@color/line_color"
        app:indicator_vertical_line_h="20dp"
        app:indicator_width="0dp" />
```	

   ***非ViewPage下代码使用***

```
easy_indicator = (EasyIndicator) findViewById(R.id.easy_indicator);
        easy_indicator.setTabTitles(new String[]{"进行中", "已完成", "已评价"});
        easy_indicator.setOnTabClickListener(new EasyIndicator.onTabClickListener() {
            @Override
            public void onTabClick(String title, int position) {
                Log.i(TAG, title + "::" + position);
            }
        });
```

   ***ViewPage下代码使用***
   
``` 
easy_indicator = (EasyIndicator) findViewById(R.id.easy_indicator);
        easy_indicator.setTabTitles(new String[]{"Tab1", "Tab2", "Tab3", "Tab4"});

        // 自定义设置
        easy_indicator.setViewPage(vp, new FragmentAdapter(getSupportFragmentManager(),
                new Fragment[]{new FragmentTab1(), new FragmentTab2(),
                        new FragmentTab3(), new FragmentTab4()}));

        // 自带设置
//        easy_indicator.setViewPageViews(new FragmentAdapter(getSupportFragmentManager(),
//                new Fragment[]{new FragmentTab1(), new FragmentTab2(),
//                        new FragmentTab3(), new FragmentTab4()}));
```
	
   ***主项目的build.gradle添加如下代码***

```
allprojects {
    repositories {
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}
```
  ***compile引入***

```
dependencies {
	compile 'com.github.LuckSiege:EasyIndicator:v1.1.1' 
}
```
	  
        
        

![image](https://github.com/LuckSiege/EasyIndicator/blob/master/image/4C47A389C02BC3FD7680CF3935F1F916.jpg)
![image](https://github.com/LuckSiege/EasyIndicator/blob/master/image/0BE57F615A3284E438FEAE624C1C4319.jpg)
![image](https://github.com/LuckSiege/EasyIndicator/blob/master/image/071D2084DC0C10531EDC1CE8B27CF464.jpg)
![image](https://github.com/LuckSiege/EasyIndicator/blob/master/image/7F8892258BE7624FF6ACE4A3BE558C43.jpg)
![image](https://github.com/LuckSiege/EasyIndicator/blob/master/image/859EEDFA1A7C7EEB8B071E93AEC37BB5.jpg)
