package carracing.domain.car.strategy;

@FunctionalInterface
public interface CarMovingCondition {
    boolean isMovable();
}
