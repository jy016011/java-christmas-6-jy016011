# 미션 - 크리스마스 프로모션

---

## 🔍 문서 설명

- 이 문서는 크리스마스 프로모션 이벤트 플래너의 기능 목록과 실행 방법을 작성한 문서입니다.
- 자세한 문제 내용은 [여기](https://github.com/jy016011/java-christmas-6-jy016011/blob/main/README.md) 에서 확인바랍니다.

## 🚀 프로젝트 소개

- 우테코 식당의 크리스마스 프로모션 이벤트 내용을 안내해주는 이벤트 플래너입니다.
- 12월 중의 날짜를 입력합니다.
- 주문할 메뉴들을 입력합니다.
- 해당 날짜와 주문 금액에 맞는 이벤트 적용 내역을 도출하여 보여줍니다.
- **Enjoy your meal**!

---

## 🛠 구현 기능 목록

1. 이벤트 플래너 소개 문구를 출력한다.

- [X] `안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.`를 출력한다.

2. 식당 예상 방문 날짜를 입력받는다.

- [X] `12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해주세요!)`를 출력한다.

3. 날짜에 대한 입력을 검증한다.

- [X] 입력이 숫자가 맞는지 확인한다.
- [X] 입력이 1이상 31이하의 숫자인지 확인한다.
- [X] 위 경우를 하나라도 만족하지 않을시 `[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.`라는 에러 메시지를 출력한다.

4. 주문할 메뉴와 메뉴별 개수를 입력받는다.

- [X] `주문하실 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)`를 출력한다.
- 실행예시에는 `주문하실 메뉴를 메뉴와 개수를 알려 주세요.`로 나와있으나, 요구사항에는 위와 같이 되어있어 어색하지 않는 어휘인 위 문구를 출력한다.


5. 메뉴와 각 메뉴의 개수에 대한 입력을 검증한다.

- [X] 메뉴 형식이 예시와 맞는지 확인한다.
- [X] 중복된 메뉴가 있는지 확인한다.
- [X] 음료만 주문했는지 확인한다.
- [X] 각 메뉴의 개수가 1이상의 숫자인지 확인한다.
- [X] 메뉴의 총 개수가 20개 초과인지 확인한다.
- [X] 메뉴판에 있는 메뉴인지 확인한다.
- [X] 위 경우를 하나라도 만족하지 않을시 `[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.`라는 에러 메시지를 출력한다.

6. 이벤트 혜택 미리 보기 머릿말을 출력한다.

- [X] `12월 {입력 날짜}일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!`를 출력한다.

7. 입력한 주문 메뉴를 출력한다.

- [X] 입력한 메뉴와 각 매뉴별 개수를 도출한다.
- [X] `<주문 메뉴>`를 출력하고 다음줄부터 입력한 메뉴들을 출력한다.
- [X] 줄마다 각 메뉴와 개수를 같이 출력한다.

8. 입력한 메뉴들의 할인 전 총 주문 금액을 출력한다.

- [X] 할인 전 총주문 금액을 구한다.
- [X] `<할인 전 총주문 금액>`을 출력하고 다음줄에 금액을 출력한다.

9. 해당하는 이벤트 내역을 도출한다

- [X] 입력한 날짜에 적용되는 이벤트들을 도출한다.
    - [X] 크리스마스 디데이 할인이 가능한지
    - [X] 평일 할인이 가능한지
    - [X] 주말 할인이 가능한지
    - [X] 특별 할인이 가능한지
- [X] 총 주문 금액에 적용되는 이벤트들을 도출한다.
    - [X] 총 주문 금액이 10,000원 이상인지
    - [X] 증정 이벤트가 가능한지

10. 해당하는 이벤트들을 바탕으로 혜택을 도출한다.

- [X] 입력한 날짜에 적용되는 이벤트에 따라 각 할인금액을 계산한다.
    - [X] 크리스마스 디데이 할인 : 1,000원으로 시작하여 1일부터 크리스마스까지 날마다 100원씩 할인액 증가
    - [X] 평일 할인 : 디저트 메뉴 갯수 1개당 2,023원 할인
    - [X] 주말 할인 : 메인 메뉴 갯수 1개당 2,023원 할인
    - [X] 특별 할인 : 이벤트 달력에 별이 있는 날에는 1,000원 할인
- [X] 증정 이벤트가 가능하면, 샴페인 1개(25,000원)를 준다.
    - [X] 12만원 이상시 증정이벤트는 가능하다.

11. 증정 메뉴를 출력한다.

- [X] `<증정 메뉴>`를 출력하고 다음줄에 **10.** 에서 도출한 증정 메뉴를 출력한다.
- [X] 증정 메뉴가 없으면 `없음`을 출력한다.

12. 혜택 내역을 출력한다.

- [X] `<혜택 내역>`을 출력하고 다음줄부터 출력한다.
- [X] **9.** 에서 도출한 내역과 **10.** 에서 계산한 내역들을 모두 각 줄마다 출력한다.
- [X] 혜택 내역이 없으면, `없음`을 출력한다.

13. 총혜택 금액을 출력한다.

- [X] `<총혜택 금액>`을 출력하고 다음줄부터 출력한다.
- [X] 할인 금액과 증정 금액을 합하여 총 혜택 금액을 구한다.

14. 할인 후 예상 결제 금액을 출력한다.

- [X] `<할인 후 예상 결제 금액>`을 출력하고 다음줄부터 출력한다.
- 총주문 금액에서 **10.** 에서 계산한 할인금액의 합을 뺴 예상 결제 금액을 구한다.

15. 12월 이벤트 배지를 출력한다.

- [X] `<12월 이벤트 배지>`를 출력하고 다음줄부터 출력한다.
- [X] **13.** 에서 구한 총혜택금액에 따라 다음을 출력한다.
    - [X] 2만원 이상: 산타
    - [X] 1만원 이상: 트리
    - [X] 5천원 이상: 별
- [X] 해당하는 배지가 없으면 `없음`을 출력한다.

16. 유효하지 않은 입력에 대한 예외를 처리한다.

- [X] `IllegalArgumentException`을 발생시킨다.
- [X] 예외가 발생한 부분부터 다시 입력받는다.

---

## 🕹 프로젝트 실행 방법

**1. 다운로드**

- git bash에서 다음의 명령어를 입력하여 `Repository`를 `clone`합니다.

```
https://github.com/jy016011/java-christmas-6-jy016011.git
```

**2. 실행**

- `java-christmas-6-jy016011/src/main/java/christmas`의 `Application.java` 파일을 실행합니다.

**3. 실행 예시**

```
안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.
12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해주세요!)
32

[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.

12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해주세요!)
3
주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)
티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-0

[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.

주문하실 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)
티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1
12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!

<주문 메뉴>
티본스테이크 1개
바비큐립 1개
초코케이크 2개
제로콜라 1개

<할인 전 총주문 금액>
142,000원

<증정 메뉴>
샴페인 1개

<혜택 내역>
크리스마스 디데이 할인: -1,200원
평일 할인: -4,046원
특별 할인: -1,000원
증정 이벤트: -25,000원

<총혜택 금액>
-31,246원

<할인 후 예상 결제 금액>
135,754원

<12월 이벤트 배지>
산타
```
