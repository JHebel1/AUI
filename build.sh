function main() {
    cd ./club-service/; bash ./build.sh; cd ..
    cd ./player-service/; bash ./build.sh; cd ..
    cd ./api-gateway/; bash ./build.sh; cd ..
    cd ./frontend/; bash ./build.sh; cd ..

}

main "$@"