public interface CellOperation {
    Cell Copy(); //копировать ячейку
    Denomination getDenomination(); //получить номинал
    void setAmount(int amount); //задать количество купюр в ячейке
    int getAmount(); //получить количество купюр в ячейке
    int getTotalSum(); //получить общую сумму денежных средств в ячейке
    int getValue(); //получить номинал купюр в ячейке
}
