# Android-SeEun
## # 3rd Seminar Assginment
> 필수과제 : Font 적용, 버튼 Selector 속성, BottomNavigation, TabLayout, ViewPager2

| 실행 화면 |
|---|
|  <img src = https://user-images.githubusercontent.com/98895198/167135312-350d242c-1de9-456e-9533-c1ab22a77c5d.gif height="600dp">  |

## 1) 코드 설명
### 1. Font 적용 : font 디렉토리 생성 후 font, fontweight 속성 추가
 ```kotlin
 <font-family xmlns:android="http://schemas.android.com/apk/res/android">
    <font
        android:font="@font/noto_sans_kr_regular"
        android:fontWeight="400" />
        
     <font
        android:font="@font/noto_sans_kr_bold"
        android:fontWeight="600"/>
 </font-family>
 ```

### 2. Button에 Selector 적용 : shape로 버튼 만들고, selector의 ```state_selected```속성 이용
```kotlin
// shape_selected.xml
<shape xmlns:android="http://schemas.android.com/apk/res/android">
    <stroke
        android:width="3px"
        android:color="#8040FF" />
    <size
        android:width="158px"
        android:height="46px" />
    <solid android:color="#FAF8FF" />
    <corners android:radius="10px" />
</shape>
```
```kotlin
//shape_unselected.xml
<shape xmlns:android="http://schemas.android.com/apk/res/android">
    <size
        android:width="163px"
        android:height="46px" />
    <solid android:color="#F7F6F9" />
    <corners android:radius="10px" />
</shape>
```

```kotlin
//selector_button
<shape xmlns:android="http://schemas.android.com/apk/res/android">
    <size
        android:width="163px"
        android:height="46px" />
    <solid android:color="#F7F6F9" />
    <corners android:radius="10px" />
</shape>
```
### 3. 원형으로 이미지 표시 : Gilde 이용
```kotlin
//build.gradle
dependencies {     
    implementation 'com.github.bumptech.glide:glide:4.12.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.12.0' 
    }
```

```kotlin
//HomeActivity.kt
        Glide.with(this)
            .load(R.drawable.seeun)
            .circleCrop()
            .into(binding.imgSeeun)
 ```
### 4. Activity 하단에 BottomNavigation 추가
menu에 이미지 지정
```kotlin
//menu_main.xml
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:id="@+id/menu_profile"
        android:icon="@drawable/profile"
        android:title="프로필"/>
    <item
        android:id="@+id/menu_home"
        android:icon="@drawable/home"
        android:title="홈"/>
    <item
        android:id="@+id/menu_camera"
        android:icon="@drawable/camera"
        android:title="카메라"/>
</menu>
```
xml 파일에 바텀네비게이션뷰 추가

```kotlin
//activity_main.xml
    <com.google.android.material.bottomnavigation.BottomNavigationView
        android:id="@+id/bnv_main"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="@color/white"
        app:itemIconTint="@color/selector_bottom_navi"
        app:itemTextColor="@color/selector_bottom_navi"
        app:layout_constraintBottom_toBottomOf="parent"
        app:menu="@menu/menu_main" />
```
```initBottomNavi()``` 으로 아이콘과 ViewPager2 연동 
```kotlin
//MainActivity.kt
private fun initBottomNavi() {
        binding.vpMain.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position : Int) {
                binding.bnvMain.menu.getItem(position).isChecked = true
            }
        })

        binding.bnvMain.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.menu_profile -> {
                    binding.vpMain.currentItem = PROFILE_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                R.id.menu_home -> {
                    binding.vpMain.currentItem = HOME_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                else -> {
                    binding.vpMain.currentItem = CAMERA_FRAGMENT
                    return@setOnItemSelectedListener true
                }
            }
        }
    }
    companion object{
        const val PROFILE_FRAGMENT = 0
        const val HOME_FRAGMENT = 1
        const val CAMERA_FRAGMENT = 2
    }
 ```         
          
### 5. HomeFragment에 TabLayout, ViewPager2 추가

xml 파일에 위치를 잡아준다.
```kotlin
//fragment_home.xml
    <com.google.android.material.tabs.TabLayout
        android:id="@+id/tab_home"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:tabTextColor="#B5B5B5"
        app:tabSelectedTextColor="#8040FF"
        app:tabIndicator="@font/noto_sans_kr_medium"
        app:tabIndicatorHeight="3dp"
        app:layout_constraintTop_toBottomOf="@+id/tv_github"
        app:tabIndicatorColor="#8040FF" />     
        
    <androidx.viewpager2.widget.ViewPager2
        android:id="@+id/vp_home"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/tab_home" />
```

adapter 생성
```kotlin
//HomeTapVpAdapter.kt
class HomeTapVpAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {
    val fragments = mutableListOf<Fragment>()
    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}
```

```initAdapter()```이용해서 adapter와 viewpager2 연동

```kotlin
//HomeFragment.kt
private fun initAdapter() {
    val fragmentList = listOf(GitFollowingFragment(), GitFollowerFragment())

    HomeTapVpAdapter = HomeTapVpAdapter(this)
    HomeTapVpAdapter.fragments.addAll(fragmentList)

    binding.vpHome.adapter = HomeTapVpAdapter
}
```
ViewPager2와 TabLayout 연동 : ```initTabLayout()```, ```TabLayoutMediator``` 활용

```kotlin
//HomeFragment.kt
    private fun initTabLayout(){
        val tabLabel = listOf("팔로잉", "팔로워")

        TabLayoutMediator(binding.tabHome, binding.vpHome) { tab, position ->
            tab.text = tabLabel[position]
        }.attach()
    }
```
### 6. TabLayout에 텍스트, 인디케이터 색상 설정하기 : TabLayout 속성 이용

```kotlin
//fragment_home.xml
        app:tabTextColor="#B5B5B5"
        app:tabSelectedTextColor="#8040FF"
        app:tabIndicator="@font/noto_sans_kr_medium"
        app:tabIndicatorHeight="3dp"
```

## 2) 새롭게 알게된 내용
#### 1. drawble에 파일 추가할 때 - 파일명에 대문자가 포함될 경우 오류가 생긴다.
#### 2. TabLayout 속성
```kotlin
//기본 텍스트 색상
app:tabTextColor="색상"
//선택된 텍스트 색상
app:tabSelectedTextColor="색상"
//인디케이터 색상
app:tabIndicatorColor="색상"
//인디케이터 선두께
app:tabIndicatorHeight="3dp"
```
#### 3. 이미지를 원형으로 나타내는 방법은 2가지가 있다. <br>
  [1] Glide 이용(이번 과제에서 사용한 방법) <br>
  [2] CircleImageView 이용
  ```kotlin
  //build.gradle
  dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    ...
    implementation 'de.hdodenhof:circleimageview:3.1.0'
    ...
}
//xml 파일
<de.hdodenhof.circleimageview.CircleImageView
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="100dp"
    android:layout_height="100dp"
    android:src="@drawable/myimage" />
    ```
