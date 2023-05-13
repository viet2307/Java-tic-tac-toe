
public class State {
    static boolean playsAsMin = false;

    char[][] field = new char[5][5];
    State[][] children = new State[5][5];

    int value;
    boolean isMin;

    public State(char[][] field, boolean isMin) {
        this.field = field;
        this.isMin = isMin;
    }

    char[][] copyField() {
		char[][] copied = new char[5][5];
		for (int i = 0; i < copied.length; i++) {
			for (int j = 0; j < copied[i].length; j++) {
				copied[i][j] = field[i][j];
			}
		}
		return copied;
	}

    public int learn() {
        if(TicTacToe.checkWinner(field)) {
            value = isMin ? 1 : -1;
            return value;
        }
        if(TicTacToe.fieldFull(field)) {
            value = 0;
            return value;
        }
        value = isMin ? 1 : -1;
        
        for(int i = 0; i < children.length; i++) {
            for(int j = 0; j < children.length; j++) {
                if(field[i][j] == ' ') {
                    char[][] childField = copyField();
                    childField[i][j] = isMin ? 'O' : 'X';
                    children[i][j] = new State(childField, !isMin);
					value = isMin ? Math.min(value, children[i][j].learn()) : Math.max(value, children[i][j].learn());
                }
            }
        }

        return value;
    }
    
    public State getChildWithValue() {

		for (int i = 0; i < children.length; i++) {
			for (int j = 0; j < children[i].length; j++) {
				if (children[i][j] != null && children[i][j].value == value) {
					return children[i][j];
				}
			}
		}
		return null;
	}


}
