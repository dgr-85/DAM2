package dao;

import java.util.ArrayList;

import model.PrizeType;

public interface PrizeTypeDAO {
	public int addPrizeType(PrizeType pt);

	public PrizeType getPrizeTypeById(int id, Boolean includePrizes);

	public Integer updatePrizeType(PrizeType pt);

	public Integer deletePrizeType(int id);

	public ArrayList<PrizeType> listAllPrizeTypes();
}
