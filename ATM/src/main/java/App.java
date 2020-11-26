public class App {
    public static void main(String[] args )
    {
        ATM atm = new ATM();

        //заполняем ячейки купюрами
        atm.fillCell(Denomination.TEN, 1);
        atm.fillCell(Denomination.FIFTY, 10);
        atm.fillCell(Denomination.HUNDRED, 10);
        atm.fillCell(Denomination.TWO_HUNDRED, 10);
        atm.fillCell(Denomination.FIVE_HUNDRED, 10);
        atm.fillCell(Denomination.THOUSAND, 10);
        atm.fillCell(Denomination.FIVE_THOUSAND, 10);

        //снимаем деньги
        atm.getCash(70);
        atm.getCash(60);
        atm.getCash(440);
        atm.getCash(150);
        atm.getCash(140);
        atm.getCash(4200);
        atm.getCash(100);
        atm.getCash(70);
        atm.getCash(64000);
        atm.getCash(100);

    }
}

