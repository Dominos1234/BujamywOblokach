#! /bin/sh
sudo pacman -Syu
# sudo pacman -Sy --noconfirm apache
sudo pacman -S --noconfirm maven
sudo pacman -S --noconfirm npm
sudo pacman -S --noconfirm postgresql
# sudo systemctl enable --now httpd
sudo -u postgres bash -c 'initdb --locale $LANG -E UTF8 -D '/var/lib/postgres/data''
sudo systemctl enable postgresql.service
sudo systemctl start postgresql.service
sudo -u postgres bash -c 'createdb champions'
psql -U postgres -d champions -c "CREATE TABLE public.champions (
	id int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
	name text NOT NULL,
	hp float4 NOT NULL,
	hp_plus float4 NOT NULL,
	hp5 float4 NOT NULL,
	hp5_plus float4 NOT NULL,
	mp float4 NOT NULL,
	mp_plus float4 NOT NULL,
	mp5 float4 NOT NULL,
	mp5_plus float4 NOT NULL,
	CONSTRAINT champions_name_unique UNIQUE (name),
	CONSTRAINT champions_pkey PRIMARY KEY (id)
);"
psql -U postgres -d champions -c "COPY champions(name, hp, hp_plus, hp5, hp5_plus, mp, mp_plus, mp5, mp5_plus) FROM '/vagrant/Database/champions.csv' DELIMITER ';' CSV HEADER;"
npm install --prefix Frontend
mvn -f Backend spring-boot:run & HOST=0.0.0.0 npm run dev --prefix Frontend