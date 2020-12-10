//ячейка для купюр
//public class Cell implements CellOperation{
public class Cell implements CellOperation {
    private final Denomination denomination;
    private int amount = 0;

    public Cell (Denomination denomination){
        this.denomination = denomination;
    }

    @Override
    public Cell copy() {
        Cell cell = new Cell(this.denomination);
        cell.amount = this.amount;
        return cell;
    }

    @Override
    public Denomination getDenomination ()
    {
        return this.denomination;
    }

    @Override
    public void setAmount(int amount) throws RuntimeException {
        if(amount >= 0) {
            this.amount = amount;
        }
        else {
            //Пока не будем останавливать приложение
            //throw new IllegalArgumentException("Число купюр должно быть положительное");
            System.out.println("Число купюр должно быть положительное");
        }
    }

    @Override
    public int getAmount() {
       return this.amount;
    }

    @Override
    public int getTotalSum() {
        return this.getValue() * this.amount;
    }

    @Override
    public int getValue() {
        return this.denomination.getValue();
    }
}
