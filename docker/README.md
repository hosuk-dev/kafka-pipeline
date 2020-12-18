# Docker 

![Generic badge](https://img.shields.io/badge/docker--compse-1.27.4-red)

## docker-compose 실행

    1) docker external network 생성
       => docker network create kafka-elk
       
    2) kafka-cluster docker compose 실행
       => cd elk-cluster => docker-compose up -d
       
    3) elk-cluster docker compose 실
       => cd kafka-cluster => docker-compose up -d
       
## 주의사항

    1) kakfa, elk 스택 모두 로컬 pc에서 띄울경우 스펙이 높아 
       docker 메모리 할당량을 6gb정도는 잡아줘야 로컬에서 무리없게 실행 가능