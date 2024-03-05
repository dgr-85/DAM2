package dao;

import java.util.ArrayList;

import model.Prize;

public interface PrizeDAO {
	public Integer addPrize(Prize p);

	public Prize getPrizeById(int id);

	public Integer updatePrize(Prize p);

	public Integer deletePrize(int id);

	public ArrayList<Prize> listAllPrizes();
}
