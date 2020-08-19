package carracing.domain;

import carracing.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private int tryCount = 0;
    private List<Car> cars = null;

    OutputView outputView = new OutputView();

    public Game(String carNames, int tryCount){
        inputValid(carNames, tryCount);

        this.tryCount = tryCount;
        this.cars = this.generateCars(carNames);

    }

    private void inputValid(String carNames, int tryCount) {
        if( carNames.trim().isEmpty() || tryCount == 0) {
            throw new IllegalArgumentException("입력값을 확인해주세요.");
        }
    }

    public void start() {
        for(int i = 0; i < this.tryCount; i++) {
            play();
        }
    }

    private List<Car> generateCars(String carNames) {
        List<Car> cars = new ArrayList<>();
        String[] carName = carNames.split(",");

        for (int i = 0; i < carName.length; i++) {
            Car car = new Car(carName[i]);
            cars.add(car);
        }
        return cars;
    }

    private void play() {
        for(Car car : cars) {
            car.setPosition(new CarPowerCondition(car.getPower()));
            this.viewOutPut(car);
        }
        System.out.println("");
    }

    private void viewOutPut(Car car) {
        outputView.getResultView(car);
    }

    public void end() {
        Winner winner = new Winner(cars);
        outputView.viewWinner(winner.getWinner());

    }
}