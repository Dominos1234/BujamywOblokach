CREATE TABLE public.champions (
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
);