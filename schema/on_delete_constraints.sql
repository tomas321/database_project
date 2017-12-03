-- drop all previous foreign key constraints 

ALTER TABLE estates
	ADD CONSTRAINT estate_location_const 
		FOREIGN KEY (location_id) REFERENCES locations(id) ON DELETE RESTRICT,
	ADD CONSTRAINT estate_arrangement_const
		FOREIGN KEY (arrangement_id) REFERENCES arrangements(id) ON DELETE RESTRICT;

ALTER TABLE open_houses
	ADD CONSTRAINT open_house_client_const
		FOREIGN KEY (client_id) REFERENCES locations(id) ON DELETE CASCADE,
	ADD CONSTRAINT open_house_estate_const
		FOREIGN KEY (estate_id) REFERENCES estates(id) ON DELETE CASCADE,
	ADD CONSTRAINT open_house_agent_const
		FOREIGN KEY (agent_id) REFERENCES agents(id) ON DELETE CASCADE;

ALTER TABLE sold_estates
	ADD CONSTRAINT sold_estate_agent_const
		FOREIGN KEY (agent_id) REFERENCES agents(id) ON DELETE CASCADE;
