package com.springboot.springbootdemo;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.hibernate.Country;
import com.springboot.hibernate.Employee;


@RestController
@RequestMapping("/api/v1")
public class SpringBootHibernateTest {
	
	@Autowired
	SessionFactory sf;
	
	//Show All Employee "Rest API 1"
	@SuppressWarnings("unchecked")
	@GetMapping("getallRecord")
	public ResponseEntity<List<Employee>> showemployeelist()
	{
		Session session=sf.openSession();
		Criteria criteria=session.createCriteria(Employee.class);
		List<Employee> employeelist=criteria.list();
		return new ResponseEntity<List<Employee>>(employeelist, HttpStatus.OK);
	}
	
	//Show All Employee which have status Active OR Inactive "Rest API 2"
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/status/{status}")
	public ResponseEntity<List<Employee>> statusemployeelist(@PathVariable("status") String status)
	{
		System.out.println("Status >> "+status);
		Session session=sf.openSession();
		Criteria criteria=session.createCriteria(Employee.class);
		criteria.add(Restrictions.eq("status", status));
		List<Employee> employeelist=criteria.list();
		return new ResponseEntity<List<Employee>>(employeelist, HttpStatus.OK);
	}
	
	
	//Show All Employee which have id "Rest API 3"
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/empid/{id}")
	public ResponseEntity<List<Employee>> idemployeelist(@PathVariable("id") int id)
	{
		System.out.println("Id >> "+id);
		Session session=sf.openSession();
		Criteria criteria=session.createCriteria(Employee.class);
		criteria.add(Restrictions.eq("id", id));
		List<Employee> employeelist=criteria.list();
		return new ResponseEntity<List<Employee>>(employeelist, HttpStatus.OK);
	}

	//Show All Employee which have name "Rest API 4"
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/showemployeesByname/{name}")
	public ResponseEntity<List<Employee>> idemployeelist(@PathVariable("name") String name)
	{
		System.out.println("Status >> "+name);
		Session session=sf.openSession();
		Criteria criteria=session.createCriteria(Employee.class);
		criteria.add(Restrictions.eq("name", name));
		List<Employee> employeelist=criteria.list();
		return new ResponseEntity<List<Employee>>(employeelist, HttpStatus.OK);
	}
	
	//Show All Employee which have date today "Rest API 5"
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/showemployeesBeforeToday")
	public ResponseEntity<List<Employee>> todayemployeelist()
	{
		/*
		 * DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		 * LocalDateTime now = LocalDateTime.now(); String date=dtf.format(now);
		 */
		Calendar calendar = Calendar.getInstance(); 
		SimpleDateFormat df = new SimpleDateFormat("yyyy/mm/dd");
		Date date = calendar.getTime();
		String fdte=df.format(date);
		System.out.println("fdte >> "+fdte);
		
		
		System.out.println("date >> "+date);
		Session session=sf.openSession();
		Criteria criteria=session.createCriteria(Employee.class);
		criteria.add(Restrictions.eq("createddtm", date));
		List<Employee> employeelist=criteria.list();
		return new ResponseEntity<List<Employee>>(employeelist, HttpStatus.OK);
	}
	
	// add Employee"Rest API 6"
		@PostMapping(value = "/addemployee")
		public ResponseEntity<String> addemployee(@RequestBody Employee employee) {
			
			System.out.println("I am in Registration Dao..");
			System.out.println("Dao >> "+employee);
			Session session=sf.openSession();
			Transaction tt=session.beginTransaction();
			session.save(employee);
			tt.commit();
			System.out.println("Employee successfully..");
			return new ResponseEntity<String>("Employee added in db successfully", HttpStatus.OK);

		}

		// add Country "Rest API 7"
		@PostMapping("addcountry")
		public ResponseEntity<String> addcountry(@RequestBody Country country) {
			System.out.println(country);
			
			Session session = sf.openSession();
			Transaction tt = session.beginTransaction();
			session.save(country);
			tt.commit();
			return new ResponseEntity<String>("Country  added in db successfully", HttpStatus.OK);

		}

		
		// add Employee"Rest API 8"
		 @DeleteMapping(value = "/deleteemployee/{id}") 
		 public String deleteemployee(@PathVariable Integer id ) {
			 
			 Session session=sf.openSession();
			Transaction tt=session.beginTransaction();
			Query query=session.createQuery("delete from Employee where id=:id");
			query.setParameter("id",id);
			query.executeUpdate();
			tt.commit();
			return "Employee Deleted Successifully";
			 
				 
		 }
		 
		//Delete Country By country Name
			@DeleteMapping(value = "/deleteCountryByName/{cname}")
			public String DeleteCountryByName(@PathVariable String cname) {
				System.out.println("I am  in delete   Record by Country Name");
				Session session = sf.openSession();
				Transaction tt = session.beginTransaction();
				Query query = session.createQuery("delete from Country where cname=:cname");
				query.setParameter("cname", cname);
				query.executeUpdate();
				tt.commit();
				return "Country  Deleted Successifully";

			}

			//Delete Country By country id
				@DeleteMapping(value = "/deleteCountryById/{cid}")
				public String deleteCountryById(@PathVariable Integer cid) {
					System.out.println("I am  in delete   Record by Country ");
					Session session =sf.openSession();
					Transaction tt = session.beginTransaction();
					Query query = session.createQuery("delete from Country where cid=:cid");
					query.setParameter("cid", cid);
					query.executeUpdate();
					tt.commit();
					return "Country  Deleted Successifully";

				}

		 
		 
		 
		// add Employee"Rest API 9"
		 @PutMapping(value="/updatecountryname")
		 public String Updatecountryname(@RequestBody Country country) {
			 
			 int cid=country.getCid();
			 String cname=country.getCname();
			 
			 Session session=sf.openSession();
			 Transaction tt=session.beginTransaction();
			 Query query = session.createQuery("update Country set cname=:cname where cid=:cid");
			 query.setParameter("cname", cname);
			 query.setParameter("cid", cid);
			 query.executeUpdate();
			 tt.commit();
			 return "Updated Country successifully";
		       
		 }
}
