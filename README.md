# Android-SeEun
## # 2nd Seminar Assginment
> 자기소개 페이지에 팔로워 목록, 레포지토리 목록 추가

| 실행 화면 |
|---|
|  <img src = https://user-images.githubusercontent.com/98895198/164644343-3be78672-d585-42be-ab13-30c22a5483ae.gif width=300dp> |
## 1) HomeActivity에 FollowerRecyclerView, RepositoryRecyclerView 추가
### 1. HomeActivity에 Fragment 띄우기

step1. HomeActivity에 FragmentContainer 생성 : ```FragmentContainerView``` 이용해서 하단에 컨테이너 추가
 ```kotlin
 <androidx.fragment.app.FragmentContainerView/>
 ```
step2. HomeActivity의 버튼 클릭 시 Fragment 전환 <br>

  &nbsp; a. fragment 전환을 위해 ```initTransactionEvent()``` 함수 정의

```kotlin
   private fun initTransactionEvent() {
        val followerFragment = FollowerFragment()
        val repositoryFragment = RepositoryFragment()
        //컨테이너에 followerFragment 추가
        supportFragmentManager.beginTransaction().add(R.id.container_home, followerFragment)
            .commit()
```
 &nbsp;  b. when문 이용해 각 버튼 클릭 시 알맞는 fragment로 replace 

```kotlin
//팔로워 목록 버튼 클릭 시
        binding.btnFollow.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            when (position) {
            //레포지토리 포지션일 때 팔로워 프래그먼트로 교체
                REPOSITORY_POSITION -> {
                    transaction.replace(R.id.container_home, followerFragment)
                    //팔로워 포지션으로 바꿔줄 것(그래야 레포지토리 버튼의 when 문도 작동 가능함!)
                    position = FOLLOWER_POSITION
                }
            }
            transaction.commit()
        }
// 레포지토리 목록 버튼 클릭 시
        binding.btnRepo.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            when (position) {
            //팔로워 포지션일 때 레포지토리 프래그먼트로 교체
                FOLLOWER_POSITION -> {
                    transaction.replace(R.id.container_home, repositoryFragment)
                    //레포지토리 포지션으로 바꿔줄 것(마찬가지!)
                    position = REPOSITORY_POSITION
                }
            }
            transaction.commit()
        }
```

```kotlin
//동반객체 이용 (사실 이건 왜 필요한지 이해 못함; 알고싶다..)
    companion object {
        const val FOLLOWER_POSITION = 1
        const val REPOSITORY_POSITION = 2
    }
 ```
 
### 2. Fragment에 RecyclerView 구현

step1. item layout, data class 생성 
- item list는 tools 속성 이용, ```ellipsize, maxLine``` 속성 활용해서 말줄임 표시

```kotlin
        android:ellipsize="end"
        android:maxLines="1"
```
-  data class에 변수 설정
```kotlin
data class FollowerData(
    val name : String,
    val introduce : String
)
```

step2. Adapter와 ViewHolder 만들기
&nbsp; a. ViewHolder를 통해 Itemlayout에 데이터를 binding할 수 있음
```kotlin
    class FollowerViewHolder(
        private val binding: ItemFollowerListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: FollowerData) {
            binding.tvFollowerName.text = data.name
            binding.tvIntro.text = data.introduce
        }
    }
 ```
 &nbsp; b. Adapter에서 3개의 함수 오버라이딩
 ```kotlin
 // ViewHolder 생성
     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val binding =
            ItemFollowerListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowerViewHolder(binding)
    }
// ViewHolder에 DataList의 데이터를 붙임
    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(followerDataList[position])
    }
// 전체 데이터 개수 반환
    override fun getItemCount(): Int = followerDataList.size
 ```
 
 step3. RecyclerView를 Layout에 배치 : ```LinearLayoutManager``` 이용
 ```kotlin
     <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/rv_repository_list"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"
        tools:listitem="@layout/item_repository_list"/>
</androidx.constraintlayout.widget.ConstraintLayout>
```

 step4. RecyclerView와 Adapter 연결 <br>
 &nbsp; a. initFollowerAdapter 함수 내에 리스트 작성
 ```kotlin
     private fun initFollowerAdapter() {
        //Adapter 초기화
        followerAdapter = FollowerAdapter()
        //Adapter와 RecyclerView 연동
        binding.rvFollowerList.adapter = followerAdapter
        followerAdapter.followerDataList.addAll(
            listOf(
                FollowerData("박세은", "스파크 피엠"),
                FollowerData("박지혜", "스파크 메인피엠"),
                FollowerData("이호재", "스파크 안드"),
                FollowerData("양수빈", "스파크 아요"),
                FollowerData("김영권", "스파크 서버")
            )
 ```
  &nbsp; b. ```initFollowerAdapter()``` 호출
 ```kotlin
        binding = FragmentFollowerBinding.inflate(layoutInflater, container, false)
        //뷰를 반환하기 전에 호출해줘야 함! 
        initFollowerAdapter()
        return binding.root
```

step5. Adapter 데이터 갱신
```kotlin
        followerAdapter.notifyDataSetChanged()
 ```
 
## 2) Grid Layout으로 구현
- ```layoutManager, spanCount``` 속성 이용해서 두 줄로 구현
```kotlin
        app:layoutManager="androidx.recyclerview.widget.GridLayoutManager"
        tools:listitem="@layout/item_follower_list"
        app:spanCount="2"
```


## 3) 새롭게 배운 내용 : 코드마다 달아둔  참고 (모두 새롭게 알게된 내용입니다)

