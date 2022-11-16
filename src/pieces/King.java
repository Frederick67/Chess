package pieces;

import src.Board;
import src.Cell;
import src.ChessPiece;

public class King implements ChessPiece{
    private final int VALUE = 999;

    private int value;
    private int color;
    private Cell cell;

    public King(int color, Cell cell) {
        this.color = color;
        this.cell = cell;
        setValue(VALUE);
    }

    public static boolean isValidateMove(Cell startCell, Cell endCell, int color) {
        if (endCell.getPiece() != null && (startCell.getPiece().getColor() == endCell.getPiece().getColor())) {
            return false;
        }
        if (startCell.getI() + 1 == endCell.getI() && startCell.getJ() == endCell.getJ()) {
            return true;
        }
        if (startCell.getI() - 1 == endCell.getI() && startCell.getJ() == endCell.getJ()) {
            return true;
        }
        if (startCell.getI() == endCell.getI() && startCell.getJ() + 1 == endCell.getJ()) {
            return true;
        }
        if (startCell.getI() == endCell.getI() && startCell.getJ() - 1 == endCell.getJ()) {
            return true;
        }
        if (startCell.getI() + 1 == endCell.getI() && startCell.getJ() + 1 == endCell.getJ()) {
            return true;
        }
        if (startCell.getI() + 1 == endCell.getI() && startCell.getJ() - 1 == endCell.getJ()) {
            return true;
        }
        if (startCell.getI() - 1 == endCell.getI() && startCell.getJ() + 1 == endCell.getJ()) {
            return true;
        }
        if (startCell.getI() - 1 == endCell.getI() && startCell.getJ() - 1 == endCell.getJ()) {
            return true;
        }
        return false;        
    }

    @Override
    public boolean isValidateMove(Cell endCell) {
        return isValidateMove(getCell(), endCell, getColor());
    }

    @Override
    public boolean move(Cell endCell) {
        if (isValidateMove(endCell)) {
            endCell.setPiece(this);
            getCell().setPiece(null);
            setCell(endCell);
            return true;
        } 
        return false;    
    }

    @Override
    public boolean isUnderAttack(Board b) {
        Cell[][] cells = b.getBoard();
        for(Cell c[] : cells){
            for(Cell cell : c){
                ChessPiece p = cell.getPiece();
                if(cell.isOccupied() && p.getColor() != getColor()){
                    if(p.isValidateMove(getCell())){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public int getPiece() {
        return ChessPiece.KING;
    }

    
    @Override
    public Cell getCell() {
        return cell;
    }
    
    @Override
    public void setCell(Cell cell) {
        this.cell.setPiece(null);
        this.cell = cell;
        this.cell.setPiece(this);
    }
    
    @Override
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getColor() == ChessPiece.BLACK_COLOR ? "♚" : "♛";
    }

    @Override
    public int[][] getPossibleMoves() {
        //make a int[][] with sizes of the Board
        //fill it with ChessPiece.POSSIBLE_TO_MOVE if the move is valid
        //fill it with ChessPiece.IMPOSSIBLE_TO_MOVE if the move is invalid
        //fill it with ChessPiece.POSSIBLE_TO_ATTACK if the move is valid and capture a piece
        //fill it with ChessPiece.YOURSELF if it is your piece

        int[][] moves = new int[getCell().getBoard().getBoard().length][getCell().getBoard().getBoard()[0].length];
        for (int i = 0; i < moves.length; i++) {
            for (int j = 0; j < moves[i].length; j++) {
                
                if(cell.getI() == i && cell.getJ() == j){
                    moves[i][j] = ChessPiece.YOURSELF;
                    continue;
                }  

                if (isValidateMove(getCell().getBoard().getCell(i, j))) {
                    if (getCell().getBoard().getCell(i, j).isOccupied()) {
                        moves[i][j] = ChessPiece.POSSIBLE_TO_ATTACK;
                    } else {
                        moves[i][j] = ChessPiece.POSSIBLE_TO_MOVE;
                    }
                } else {
                    moves[i][j] = ChessPiece.IMPOSSIBLE_TO_MOVE;
                }
            }
        }
        return moves;
    } 
    
}
