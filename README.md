# InputLogin
安卓高级xml布局（一）高级输入框EditText设计
# **安卓高级xml布局（一）高级输入框EditText设计**
转载请注明来源
github链接欢迎star：
https://github.com/AndroidMsky/InputLogin
代码连接
http://download.csdn.net/detail/androidmsky/9274037

欢迎加安卓开发交流群：308372687（博主尽可能帮助大家）

###**今天给大家介绍一下如何实现一款简约时尚的安卓登陆界面。大家先看一下效果图**
![这里写图片描述](http://img.blog.csdn.net/20151116194335542)




###**当用户输入时动态出现删除按钮**



![这里写图片描述](http://img.blog.csdn.net/20151116194349528)
![这里写图片描述](http://img.blog.csdn.net/20151116194400746)


现在先罗列一下技术点：
1.如何使用圆角输入框和按钮背景
2.如何实现“手机号”、“密码”后面的竖线
3.如何嵌套输入框的布局
4.如何监听输入框的输入事件及删除按钮的动态显示隐藏

### 1.如何使用圆角输入框和按钮背景

安卓为开发者准备了shape这个xml标签，用于自定义一些形状。
那么我就来定义一个白色的输入框背景。代码如下：
```xml
    <!-- 形状 -->
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle" >

    <solid android:color="#ffffff" />
    <!-- 边框 -->
    <stroke
        android:width="1dip"
        android:color="#ffffff" />
    <!-- 内填充颜色 -->
    <padding
        android:bottom="10dp"
        android:left="10dp"
        android:right="10dp"
        android:top="10dp" />
    <!-- 圆角 -->
    <corners android:radius="6dp" />

</shape>
```
将其设置成任何View的background就可以了
```xml
android:background="@drawable/shape_wihte_frame"

```
### 2.如何实现“手机号”、“密码”后面的竖线
这个其实很简单，只需书写一个竖线即可，宽度为1dp或者1px（或你认为更合适的数值）。
```xml
   <View
                android:id="@+id/view1"
                android:layout_width="1dip"
                android:layout_height="fill_parent"
                android:layout_centerVertical="true"
                android:layout_gravity="center_horizontal"
                android:layout_marginLeft="2dp"
                android:layout_marginRight="2dp"
                android:layout_toRightOf="@+id/textView1"
                android:background="#EEEFFF" />


```
### 3.如何嵌套输入框的布局
安卓给我们提供了多种布局，但是你用任何一种都没办法把界面设计好。必须嵌套，很多新手不敢去嵌套，大家一定要大胆的去嵌套去使用各种布局，一定会组合出炫酷的效果的。这里布局很简单仅仅是一层嵌套（整个页面布局嵌套输入框的布局）。

``` xml
 <RelativeLayout
            android:layout_width="fill_parent"
            android:layout_height="fill_parent"
            android:layout_alignParentTop="true"
            android:layout_centerHorizontal="true"
            android:background="@drawable/shape_wihte_frame" >

            <TextView
                android:id="@+id/textView1"
                android:layout_width="40dp"
                android:layout_height="wrap_content"
                android:layout_alignParentLeft="true"
                android:layout_centerVertical="true"
                android:lines="1"
                android:padding="1dp"
                android:text="手机号"
                android:textSize="11sp" />

            <View
                android:id="@+id/view1"
                android:layout_width="1dip"
                android:layout_height="fill_parent"
                android:layout_centerVertical="true"
                android:layout_gravity="center_horizontal"
                android:layout_marginLeft="2dp"
                android:layout_marginRight="2dp"
                android:layout_toRightOf="@+id/textView1"
                android:background="#EEEFFF" />

            <EditText
                android:id="@+id/phonenumber"
                android:layout_width="wrap_content"
                android:layout_height="40dp"
                android:layout_centerVertical="true"
                android:layout_marginLeft="2dp"
                android:layout_toRightOf="@+id/view1"
                android:background="@drawable/transparent"
                android:ems="19"
                android:hint="请输入手机号"
                android:inputType="phone"
                android:padding="1dp"
                android:textSize="12sp" >

                <requestFocus />
            </EditText>

            <ImageView
                android:id="@+id/del_phonenumber"
                android:layout_width="20dp"
                android:layout_height="20dp"
                android:layout_alignParentRight="true"
                android:layout_centerVertical="true"
                android:layout_marginRight="3dp"
                android:src="@drawable/text_del"
                android:visibility="invisible" />
        </RelativeLayout>
```

### 4.如何监听输入框的输入事件及删除按钮的动态显示隐藏
思想很简单，就是监听EditText的输入事件，之后如果输入长度大于0就显示后面的删除按钮，如果=0就隐藏删除按键，点击删除按钮就清空输入框。在这里我写出了一个工具类方便大家调用。高内聚低耦合是我们共同的追求。

``` java
public class EditTextClearTools {
	public static void addclerListener(final EditText e1, final ImageView m1) {

		e1.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				// 监听如果输入串长度大于0那么就显示clear按钮。
				String s1 = s + "";
				if (s.length() > 0) {
					m1.setVisibility(View.VISIBLE);
				} else {
                    m1.setVisibility(View.INVISIBLE);
				}

			}
		});

		m1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 清空输入框
				e1.setText("");

			}
		});

	}

}
```
### 主程序代码

``` java
public class MainActivity extends Activity {
	EditText e1, e2;
	ImageView m1, m2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_user_login);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		e1 = (EditText) findViewById(R.id.phonenumber);
		e2 = (EditText) findViewById(R.id.password);
		m1 = (ImageView) findViewById(R.id.del_phonenumber);
		m2 = (ImageView) findViewById(R.id.del_password);
		// 添加清楚监听器大气
		EditTextClearTools.addclerListener(e1, m1);
		EditTextClearTools.addclerListener(e2, m2);

	}

}
```

xml对于安卓程序的重要性相信大家在开发的路程中会慢慢体会到。在这里仅仅是给了一个简单的例子，后面会更新很多很好的安卓技术博客。我是安卓天，感谢大家支持。希望大家多多沟通交流
欢迎加安卓开发交流群：308372687（博主尽可能帮助大家）

github链接欢迎star：
https://github.com/AndroidMsky/InputLogin
