# Android-SeEun
## # 1st Seminar Assginment
> 로그인, 회원가입, 자기소개 페이지

| 로그인 - 자기소개  | 회원가입 |
|---|---|
|   |   |

### 1) 로그인 페이지(SignInActivity)
#### 1. 로그인 버튼 클릭 시 <br>

 ✔ 아이디, 비밀번호가 모두 입력된 경우
 - HomeActivity로 이동
 - "로그인 성공" 토스트 메시지 출력 <br>
 
 ✔ 둘 중 하나라도 비어있는 경우
 - "아이디/비밀번호를 확인해주세요" 토스트 메시지 출력 <br>

1> 입력 조건 충족 여부 확인 - ```isBlank()``` 활용
```kotlin
    private fun isInputComplete(): Boolean {
        val id = binding.idEditText.text
        val pw = binding.pwEditText.text
        val isIdNull = id.isBlank()
        val isPwNull = pw.isBlank()

        return !isIdNull && !isPwNull
    }
```
2> 로그인 버튼 눌렀을 때
```kotlin
        binding.loginBtn.setOnClickListener {
            if (isInputComplete()) {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "아이디/비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
            }
        }
```
#### 2. EditTextView 비밀번호 입력내용 가리기 : InputType 속성 활용
```kotlin
       android:inputType="textPassword"
```
#### 3. EditTextView 미리보기 글씨 : hint 속성 활용
```kotlin
        android:hint="아이디를 입력해주세요."
```
### 2) 회원가입 페이지(SignUpActivity)
- 입력 조건 충족 검사, 비밀번호 입력내용 가리기, 미리보기 글씨, 토스트 메시지는 로그인 페이지와 동일
- 회원가입 완료 버튼 클릭 시 SignInActivity로 이동 - ```finish()``` 활용
```kotlin
            if (isInputComplete(name, id, pw)) {
                val intent = Intent(this, SignInActivity::class.java)
                startActivity(intent)
                if(!isFinishing) finish()
            } else {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다.", Toast.LENGTH_SHORT).show()
            }
        }
```
### 3) 자기소개 페이지(HomeActivity)
- ImageView, TextView 활용

### 4) 새롭게 배운 내용
- intent를 활용해 화면 전환하는 것
- isBlank() 활용하여 입력 조건 충족 여부 확인
관련 내용)
-isEmpty() : String 안에 공백(Whitespace) 혹은 빈 문자열이 있으면 true를 반환
-isBlank() : 문자열이 빈 문자열이면 true를 반환
