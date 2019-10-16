# SWExpertAcademy_MockTest_Java_4014

## SW Expert Academy 4014. [모의 SW 역량테스트] 활주로 건설

### 1. 문제설명

출처: https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeW7FakkUDFAVH

input으로 첫째줄에 `N`과 `X`가 들어온다. `N * N` 지형에 가로와 세로에 활주로를 설치하려고 하며 조건으로 높이는 `1`이 길이는 `X`여야만 한다. 지형정보는 지형의 높이를 의미하며 끊어지지 않고 활주로를 설치할 수 있는 개수를 출력하는 문제.


[입력]
> 입력의 맨 첫 줄에는 총 테스트 케이스의 개수 `T` 가 주어지고,
> 그 다음 줄부터 `T` 개의 테스트 케이스가 주어진다.
> 각 테스트 케이스의 첫 번째 줄에는 지도의 한 변의 크기인 `N` 과 경사로의 길이 `X` 가 주어진다.
> 다음 `N` 개의 줄에는 `N * N` 크기의 지형 정보가 주어진다.

[출력]
> 테스트 케이스 개수만큼 `T` 개의 줄에 각각의 테스트 케이스에 대한 답을 출력한다.
> 각 줄은 `#t` 로 시작하고 공백을 하나 둔 다음 정답을 출력한다. ( `t` 는 `1` 부터 시작하는 테스트 케이스의 번호이다. )
> 정답은 활주로를 건설할 수 있는 경우의 수이다.

### 2. 풀이

Brute force 하게 풀었다. 지형정보를 받은 다음에 지형을 가로와 세로로보아 값들을 `String`으로 나열하여 지형의 모양과 개수를 `HashMap`에 담았다. 담을때 길이 반대로 생겨도 같은 결과가 나올것이므로 `StringBuilder.reverse()`를 사용하여 같은 모양의 길을 찾아 개수를 세주었다. `Map`에서 `String`을 하나씩 꺼내 순차적으로 읽으며 문제의 조건을 만족하는지 검사하며 가능할 경우만 `Map`에서 값만큼을 더해주어 계산량을 줄였다.

```java

StringTokenizer st = new StringTokenizer(br.readLine());
int N = Integer.parseInt(st.nextToken());
int X = Integer.parseInt(st.nextToken());

lines.clear();

int map[][] = new int[N][N];
for (int i = 0; i < N; i++) {
  String s = br.readLine();
  st = new StringTokenizer(s);
  for (int j = 0; j < N; j++) {
    map[i][j] = Integer.parseInt(st.nextToken());
  }
  s = s.replace(" ", "").trim();
  String reverse = new StringBuilder(s).reverse().toString();
  if (lines.containsKey(s)) {
    lines.put(s, lines.get(s) + 1);
  } else if (lines.containsKey(reverse)) {
    lines.put(reverse, lines.get(reverse) + 1);
  } else {
    lines.put(s, 1);
  }
}

for (int i = 0; i < N; i++) {
  StringBuilder sb = new StringBuilder();
  for (int j = 0; j < N; j++) {
    sb.append(map[j][i]);
  }
  String reverse = sb.reverse().toString();
  if (lines.containsKey(sb.toString())) {
    lines.put(sb.toString(), lines.get(sb.toString()) + 1);
  } else if (lines.containsKey(reverse)) {
    lines.put(reverse, lines.get(reverse) + 1);
  } else {
    lines.put(sb.toString(), 1);
  }
}

int total = 0;
for (Iterator<String> it = lines.keySet().iterator(); it.hasNext();) {
  String key = it.next();
  char prev = 0;
  int count = 0;
  char cur = key.charAt(0);
  boolean flag = true;

  for (int j = 0; j < N; j++) {
    prev = cur;
    cur = key.charAt(j);
    if (prev == cur) {
      count++;
      if (flag == false && count >= X) {
        flag = true;
        count = 0;
      }
    } else if (prev + 1 == cur) {
      if (count >= X) {
        flag = true;
        count = 1;
      } else {
        flag = false;
        break;
      }
    } else if (prev == cur + 1) {
      if (flag == false) {
        break;
      } else {
        count = 1;
        flag = false;
      }
    } else {
      flag = false;
      break;
    }

  }

  if (flag) {
    total += lines.get(key);
  }
}

System.out.println("#" + test_case + " " + total);

}

```
