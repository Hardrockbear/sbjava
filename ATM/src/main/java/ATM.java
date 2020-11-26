import java.util.ArrayList;
import java.util.List;

public class ATM implements ATMOperation {
    private List<Cell> cellList = new ArrayList();

    public ATM (){
        for (Denomination denomination : Denomination.values())
        {
            this.cellList.add(new Cell(denomination));
        }
    }

    @Override
    public void fillCell(Denomination denomination, int amount) {
        for (Cell cell : cellList)
        {
            if(cell.getDenomination() == denomination)
            {
                cell.setAmount(amount);
            }
        }
    }

    @Override
    public void getCash(int cashAmount) {
        int totalAmount = this.getTotalAmount();
        int tempCashAmount = cashAmount;

        if(totalAmount >= cashAmount){ //Если в АТМ достаточно средств
            List<Cell> cellListTemp = new ArrayList();

            for (Cell cell : cellList) //копия списка ячеек
            {
                cellListTemp.add(cell.Copy());
            }

            for (int i = cellList.size() - 1; i >= 0; i--) //Идем по ячейкам и собираем запрошенную сумму наименьшим числом купюр
            {
                Cell currentCell = cellList.get(i);

                if(currentCell.getAmount() != 0 && tempCashAmount != 0 && tempCashAmount >= currentCell.getValue())
                {
                    int num = tempCashAmount / currentCell.getValue(); //доступное количество купюр

                    if(num < currentCell.getAmount())
                    {
                        cellListTemp.get(i).setAmount(currentCell.getAmount() - num);
                        tempCashAmount = tempCashAmount - num * currentCell.getValue();
                    }
                    else {
                        tempCashAmount = tempCashAmount - cellListTemp.get(i).getAmount() * currentCell.getValue();
                        cellListTemp.get(i).setAmount(0);
                    }
                }
            }

            if(tempCashAmount == 0) //смогли собрать запрошенную сумму
            {
                this.cellList = cellListTemp; //меняем значения в ячейках
                System.out.println(String.format("Выдано %s денежных средств", cashAmount));
                System.out.println(String.format("В АТМ осталось %s денежных средств", totalAmount - cashAmount));
            }
            else {
                System.out.println("В АТМ недостаточно купюр необходимого номинала для выдачы запрошенной суммы");
            }
        }
        else {
            //Пока не будем останавливать приложение
            //throw new RuntimeException("В АТМ недостаточно денежных средств!");
            System.out.println("В АТМ недостаточно денежных средств");
        }

        System.out.println("-------------------");
    }

    @Override
    public int getTotalAmount() {
        int totalAmount = 0;

        for (Cell cell : cellList)
        {
            totalAmount += cell.getTotalSum();
        }

        return totalAmount;
    }
}
