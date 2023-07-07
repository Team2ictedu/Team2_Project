select t2.pa_name, t1.pr_con, t1.m_id
		from place_review t1
		join place_all t2 on t1.pa_num = t2.pa_num
		where t2.pa_num = t1.pa_num;