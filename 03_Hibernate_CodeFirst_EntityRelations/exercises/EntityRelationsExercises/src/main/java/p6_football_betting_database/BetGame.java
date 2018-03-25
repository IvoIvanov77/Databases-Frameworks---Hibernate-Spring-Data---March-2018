package p6_football_betting_database;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bet_games")
public class BetGame  implements Serializable{


    @Id
    @ManyToOne(targetEntity = Game.class)
    private Game game;

    @ManyToOne(targetEntity = Bet.class)
    private Bet bet;

    @OneToOne(targetEntity = ResultPrediction.class)
    private ResultPrediction resultPrediction;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Bet getBet() {
        return bet;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
    }

    public ResultPrediction getResultPrediction() {
        return resultPrediction;
    }

    public void setResultPrediction(ResultPrediction resultPrediction) {
        this.resultPrediction = resultPrediction;
    }
}
