# EasyIndicator

一款简单的tab指示器，带平移动画效果，代码简单

使用方法：
      
      
      app:indicator_bottom_height="" 底部指示器高度
      
      app:indicator_bottom_line_color="" 底部线条颜色
      
      app:indicator_bottom_line_height="" 底部线条高度
      
      app:indicator_width="" tab宽度
      
      app:indicator_height="" tab高度
      
      app:indicator_isBottom_line="" 是否显示底部线条
      
      app:indicator_line_color="" 底部指示器颜色
      
      app:indicator_normal_color="" tab选中颜色
      
      app:indicator_selected_color="" tab未选中颜色
      
      app:indicator_textSize="" tab字体大小
      
      app:indicator_vertical_line="" 垂直分割线条宽度
      
      app:indicator_vertical_line_color="" 垂直线条颜色
      
      app:indicator_vertical_line_h=""  垂直线条高度
      
      <com.luck.indicator.EasyIndicator<br>
        android:id="@+id/easy_indicator"<br>
        android:layout_width="match_parent"<br>
        android:layout_height="wrap_content"<br>
        app:indicator_bottom_height="3dp"<br>
        app:indicator_bottom_line_color="@color/line_color"<br>
        app:indicator_bottom_line_height="1dp"<br>
        app:indicator_height="42dp"<br>
        app:indicator_isBottom_line="true"<br>
        app:indicator_line_color="@color/app_color"<br>
        app:indicator_normal_color="@color/color_70"<br>
        app:indicator_selected_color="@color/app_color"<br>
        app:indicator_textSize="14sp"<br>
        app:indicator_vertical_line="1dp"<br>
        app:indicator_vertical_line_color="@color/line_color"<br>
        app:indicator_vertical_line_h="20dp"<br>
        app:indicator_width="0dp" /><br>
	
   ***app 主项目的build.gradle***
   
    
    allprojects {
    
		    repositories {
       
			   maven { url 'https://jitpack.io' }
      
		         }
	    }
    
    dependencies {
  
	        compile 'com.github.LuckSiege:EasyIndicator:v1.0.2'
         
 	  }
	  
        
        

![image](https://github.com/LuckSiege/EasyIndicator/blob/master/image/4C47A389C02BC3FD7680CF3935F1F916.jpg)
![image](https://github.com/LuckSiege/EasyIndicator/blob/master/image/7F8892258BE7624FF6ACE4A3BE558C43.jpg)
![image](https://github.com/LuckSiege/EasyIndicator/blob/master/image/859EEDFA1A7C7EEB8B071E93AEC37BB5.jpg)
