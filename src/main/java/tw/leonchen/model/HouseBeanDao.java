package tw.leonchen.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class HouseBeanDao implements IHouseBeanDao{
	private Session session;
	
	
	public HouseBeanDao(Session session) {
		this.session=session;
	}
	
	public HouseBean insert(HouseBean hBean) {
		HouseBean resultBean = session.get(HouseBean.class, hBean.getHouseid());
		if(resultBean==null) {
			session.save(hBean);
			return hBean;
		}
		return null;
	}
	
	public HouseBean selectById(int houseid) {
		return session.get(HouseBean.class, houseid);
	}
	
	public List<HouseBean> selectAll(){
		Query<HouseBean> query = session.createQuery("from HouseBean",HouseBean.class);
		return query.list();
	}
	
	public HouseBean update(int houseid,String housename) {
		HouseBean resultBean = session.get(HouseBean.class, houseid);
		if(resultBean!=null) {
			resultBean.setHousename(housename);
		}
		return resultBean;
	}
	
	public boolean deleteById(int houseid) {
		HouseBean resultBean = session.get(HouseBean.class, houseid);
		if(resultBean!=null) {
			session.delete(resultBean);
			return true;
		}
		return false;
	}

}
