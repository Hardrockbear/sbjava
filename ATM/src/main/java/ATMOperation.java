//Операции ATM
public interface ATMOperation {
    void fillCell(Denomination denomination, int amount); //заполнить ячейку
    void getCash (int amount); //снять деньги
    int getTotalAmount(); //получить общую сумму денежных средств доступных для выдачи в АТМ
}
