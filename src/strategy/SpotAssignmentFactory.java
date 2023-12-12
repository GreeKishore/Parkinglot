package strategy;

import Models.SpotAssignmentStrategyType;

public class SpotAssignmentFactory {
    public static SpotAssignmentStrategy getSpotAssignmentStrategyByType(SpotAssignmentStrategyType spotAssignmentStrategyType){
        return switch(spotAssignmentStrategyType){
            case RANDOM -> new RandomSpotAssignmentStrategy();
            default -> new RandomSpotAssignmentStrategy();
        };
    }
}
