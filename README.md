### PacketEntityManager
플러그인, 애드온, 혹은 그 이상.

#### PacketEntityManager에 대하여
PacketEntityManager은 패킷으로 구축된 마인크래프트 엔티티 트래커입니다.
해당 라이브러리로 생성된 엔티티는 버킷 시스템에서 관여하지 않는 독립된 개체가 됩니다.

#### 작동 원리
PacketEntityManager은 버킷의 난독화된 코드 중에서 엔티티부분만 걸러낸 후, 
데이터들을 다시 한번 정제하여 전송 가능한 패킷으로 만듭니다.

#### 버전 규칙
버전은 메이저.마이너.버그픽스(#기능 번호 B빌드횟수)의 형식을 띕니다.<br>
메이저 버전은 프로젝트의 구조가 완전히 변경되었을 경우 증가합니다.<br>
마이너 버전은 기능 업데이트시 증가합니다.<br>
기능 업데이트는 실험적 기능(Experimental)이 추가되었을때나 정식 기능으로 편입되었을때도 증가합니다.<br>
버그 픽스는 프로젝트에 버그로 인한 수정이 발생하였을 떄 증가합니다.
기능 번호는 실험적 기능(Experimental)이 존재할 때 버전에 Prefix로 붙습니다.<br>
실험적 기능이 정식 기능으로 편입되거나 제거되었을 경우, 해당 기능의 빌드 정보는 제거됩니다.<br>

#### 현재 지원하는 기능
- 마인크래프트 버전 1.12 (JE)<br>
ㄴ 플레이어 엔티티<br>
ㄴ 아머스탠드 엔티티<br>

#### 현재 진행중인 기능
└ 버킷 의존 독립 (#1 B1)

#### 제작될 기능
- 마인크래프트 NMS로부터 독립 *최고 우선순위*
- 패킷 엔티티에 적용되는 중력 엔진<br>
- 프로토콜립 의존성 제거<br>
- 추가 버전 지원<br>
- 마인크래프트 버전 1.12<br>
ㄴ 늑대 엔티티<br>
ㄴ 말 엔티티<br>
ㄴ 좀비 엔티티<br>
ㄴ 주민 엔티티<br>
ㄴ 아이템 엔티티<br>
ㄴ 경험치 엔티티<br>
ㄴ 포션 효과(Area effect cloud) 엔티티<br>
ㄴ 스켈레톤 엔티티<br>
ㄴ 위더 스켈레톤 엔티티 <br>
ㄴ 스트레이(Stray)<br>
ㄴ 엘더 가디언<br>
ㄴ 알(Egg)<br>
ㄴ 화살,눈덩이 등 투사체<br>
ㄴ 아이템 프레임<br>
ㄴ 허스크<br>
ㄴ 이보커<br>
ㄴ 벡스<br>
ㄴ 빈디케이터<br>
ㄴ 보트<br>
ㄴ 마인카트 종류<br>
ㄴ 크리퍼<br>
ㄴ 자이언트<br>
ㄴ 슬라임<br>
ㄴ 돼지 좀비<br>
ㄴ 엔더맨<br>
ㄴ 동굴 거미<br>
ㄴ 좀벌레<br>
ㄴ 블레이즈 <br>
ㄴ 박쥐<br>
ㄴ 돼지<br>
ㄴ 양<br>
ㄴ 소<br>
ㄴ 닭<br>
ㄴ 오징어<br>
ㄴ 버섯소<br>
ㄴ 토끼<br>
ㄴ 북극곰<br>
ㄴ 라마<br>
ㄴ 앵무새<br>
ㄴ 엔더 크리스탈<br>