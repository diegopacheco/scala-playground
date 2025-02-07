create table IF NOT EXISTS COMPUTERS (ID INTEGER NOT NULL,NAME VARCHAR NOT NULL,MANUFACTURER_ID INTEGER NOT NULL);
create table IF NOT EXISTS COMPANIES (ID INTEGER NOT NULL,NAME VARCHAR NOT NULL);

insert into COMPANIES values (1,'Apple Inc.');
insert into COMPANIES values (2,'Thinking Machines');
insert into COMPANIES values (3,'RCA');
insert into COMPANIES values (4,'Netronics');
insert into COMPANIES values (5,'Tandy Corporation');
insert into COMPANIES values (6,'Commodore International');
insert into COMPANIES values (7,'MOS Technology');
insert into COMPANIES values (8,'Micro Instrumentation and Telemetry Systems');
insert into COMPANIES values (9,'IMS Associates,  Inc.');
insert into COMPANIES values (10,'Digital Equipment Corporation');
insert into COMPANIES values (11,'Lincoln Laboratory');
insert into COMPANIES values (12,'Moore School of Electrical Engineering');
insert into COMPANIES values (13,'IBM');
insert into COMPANIES values (14,'Amiga Corporation');
insert into COMPANIES values (15,'Canon');
insert into COMPANIES values (16,'Nokia');
insert into COMPANIES values (17,'Sony');
insert into COMPANIES values (18,'OQO');
insert into COMPANIES values (19,'NeXT');
insert into COMPANIES values (20,'Atari');
insert into COMPANIES values (22,'Acorn ComputersRow');
insert into COMPANIES values (23,'Timex Sinclair');
insert into COMPANIES values (24,'Nintendo');
insert into COMPANIES values (25,'Sinclair Research Ltd');
insert into COMPANIES values (26,'Xerox');
insert into COMPANIES values (27,'Hewlett-Packard');
insert into COMPANIES values (28,'Zemmix');
insert into COMPANIES values (29,'ACVS');
insert into COMPANIES values (30,'Sanyo');
insert into COMPANIES values (31,'Cray');
insert into COMPANIES values (32,'Evans & Sutherland');
insert into COMPANIES values (33,'E.S.R. Inc.');
insert into COMPANIES values (34,'OMRON');
insert into COMPANIES values (35,'BBN Technologies');
insert into COMPANIES values (36,'Lenovo Group');
insert into COMPANIES values (37,'ASUS');
insert into COMPANIES values (38,'Amstrad');
insert into COMPANIES values (39,'Sun Microsystems');
insert into COMPANIES values (40,'Texas Instruments');
insert into COMPANIES values (41,'HTC Corporation');
insert into COMPANIES values (42,'Research In Motion');
insert into COMPANIES values (43,'Samsung Electronics');


insert into COMPUTERS values (1,'MacBook Pro 15.4 inch', 1);
insert into COMPUTERS values (2,'CM-2a', 2);
insert into COMPUTERS values (3,'CM-200', 2);
insert into COMPUTERS values (4,'CM-5e', 2);
insert into COMPUTERS values (5,'CM-5', 2);
insert into COMPUTERS values (6,'MacBook Pro', 1);
insert into COMPUTERS values (7,'Apple IIe', 1);
insert into COMPUTERS values (8,'Apple IIc', 1);
insert into COMPUTERS values (9,'Apple IIGS', 1);
insert into COMPUTERS values (10,'Apple IIc Plus', 1);
insert into COMPUTERS values (11,'Apple II Plus', 1);
insert into COMPUTERS values (12,'Apple III', 1);
insert into COMPUTERS values (13,'Apple Lisa', 1);
insert into COMPUTERS values (14,'CM-2', 2);
insert into COMPUTERS values (15,'Connection Machine', 2);
insert into COMPUTERS values (16,'Apple II', 1);
insert into COMPUTERS values (17,'Apple III Plus', 1);
insert into COMPUTERS values (18,'COSMAC ELF', 3);
insert into COMPUTERS values (19,'COSMAC VIP', 3);
insert into COMPUTERS values (20,'ELF II', 4);
insert into COMPUTERS values (21,'Macintosh', 1);
insert into COMPUTERS values (22,'Macintosh II', 0);
insert into COMPUTERS values (23,'Macintosh Plus', 1);
insert into COMPUTERS values (24,'Macintosh IIfx', 0);
insert into COMPUTERS values (25,'iMac', 1);
insert into COMPUTERS values (26,'Mac Mini', 1);
insert into COMPUTERS values (27,'Mac Pro', 1);
insert into COMPUTERS values (28,'Power Macintosh', 1);
insert into COMPUTERS values (29,'PowerBook', 1);
insert into COMPUTERS values (30,'Xserve', 0);
insert into COMPUTERS values (31,'Powerbook 100', 0);
insert into COMPUTERS values (32,'Powerbook 140', 0);
insert into COMPUTERS values (33,'Powerbook 170', 0);
insert into COMPUTERS values (34,'PowerBook Duo', 0);
insert into COMPUTERS values (35,'PowerBook 190', 0);
insert into COMPUTERS values (36,'Macintosh Quadra', 1);
insert into COMPUTERS values (37,'Macintosh Quadra 900', 0);
insert into COMPUTERS values (38,'Macintosh Quadra 700', 0);
insert into COMPUTERS values (39,'Macintosh LC', 1);
insert into COMPUTERS values (40,'Macintosh LC II', 1);
insert into COMPUTERS values (41,'Macintosh LC III', 1);
insert into COMPUTERS values (42,'Macintosh LC III+', 0);
insert into COMPUTERS values (43,'Macintosh Quadra 605', 1);
insert into COMPUTERS values (44,'Macintosh LC 500 series', 0);
insert into COMPUTERS values (45,'TRS-80 Color ComputersRow', 5);
insert into COMPUTERS values (46,'Acorn System 2', 0);
insert into COMPUTERS values (47,'Dragon 32/64', 0);
insert into COMPUTERS values (48,'MEK6800D2', 0);
insert into COMPUTERS values (49,'Newbear 77/68', 0);
insert into COMPUTERS values (50,'Commodore PET', 6);
insert into COMPUTERS values (51,'Commodore 64', 6);
insert into COMPUTERS values (52,'Commodore 64C', 0);
insert into COMPUTERS values (53,'Commodore SX-64', 6);
insert into COMPUTERS values (54,'Commodore 128', 6);
insert into COMPUTERS values (55,'Apple I', 1);
insert into COMPUTERS values (56,'KIM-1', 7);
insert into COMPUTERS values (57,'Altair 8800', 8);
insert into COMPUTERS values (58,'IMSAI 8080', 9);
insert into COMPUTERS values (59,'IMSAI Series Two', 0);
insert into COMPUTERS values (60,'VAX', 10);
insert into COMPUTERS values (61,'VAX 11/780', 10);
insert into COMPUTERS values (62,'VAX 11/750', 10);
insert into COMPUTERS values (63,'TX-2', 11);
insert into COMPUTERS values (64,'TX-0', 11);
insert into COMPUTERS values (65,'Whirlwind', 11);
insert into COMPUTERS values (66,'ENIAC', 12);
insert into COMPUTERS values (67,'IBM PC', 13);
insert into COMPUTERS values (68,'Macintosh Classic', 0);
insert into COMPUTERS values (69,'Macintosh Classic II', 1);
insert into COMPUTERS values (70,'Amiga', 14);
insert into COMPUTERS values (71,'Amiga 1000', 6);
insert into COMPUTERS values (72,'Amiga 500', 6);
insert into COMPUTERS values (73,'Amiga 500+', 0);
insert into COMPUTERS values (74,'Amiga 2000', 6);
insert into COMPUTERS values (75,'Amiga 3000', 6);
insert into COMPUTERS values (76,'Amiga 600', 6);
insert into COMPUTERS values (77,'Macintosh 128K', 1);
insert into COMPUTERS values (78,'Macintosh 512K', 1);
insert into COMPUTERS values (79,'Macintosh SE', 1);
insert into COMPUTERS values (80,'Macintosh SE/30', 1);
insert into COMPUTERS values (81,'Canon Cat', 15);
insert into COMPUTERS values (82,'Nokia 770', 16);
insert into COMPUTERS values (83,'Nokia N800', 16);
insert into COMPUTERS values (84,'Mylo', 17);
insert into COMPUTERS values (85,'OQO 02', 18);
insert into COMPUTERS values (86,'OQO 01+', 0);
insert into COMPUTERS values (87,'Pinwheel calculator', 0);
insert into COMPUTERS values (88,'iBook', 1);
insert into COMPUTERS values (89,'MacBook', 1);
insert into COMPUTERS values (90,'NeXTstation', 19);
insert into COMPUTERS values (91,'NeXTcube', 19);
insert into COMPUTERS values (92,'NeXTstation Color Turbo', 0);
insert into COMPUTERS values (93,'NeXTstation Color', 0);
insert into COMPUTERS values (94,'NeXTstation Turbo', 0);
insert into COMPUTERS values (95,'NeXTcube Turbo', 19);
insert into COMPUTERS values (96,'NeXTcube 040', 19);
insert into COMPUTERS values (97,'NeXTcube 030', 19);
insert into COMPUTERS values (98,'Tinkertoy Tic-Tac-Toe ComputersRow', 0);
insert into COMPUTERS values (99,'Z3', 0);
insert into COMPUTERS values (100,'Z4', 0);
insert into COMPUTERS values (101,'Z1', 0);
insert into COMPUTERS values (102,'Z2', 0);
insert into COMPUTERS values (103,'Wang 2200', 0);
insert into COMPUTERS values (104,'Wang VS', 0);
insert into COMPUTERS values (105,'Wang OIS', 0);
insert into COMPUTERS values (106,'BBC Micro', 22);
insert into COMPUTERS values (107,'IBM 650', 13);
insert into COMPUTERS values (108,'Cray-1', 0);
insert into COMPUTERS values (109,'Cray-3', 0);
insert into COMPUTERS values (110,'Cray-2', 0);
insert into COMPUTERS values (111,'Cray-4', 0);
insert into COMPUTERS values (112,'Cray X1', 0);
insert into COMPUTERS values (113,'Cray XD1', 0);
insert into COMPUTERS values (114,'Cray T3D', 0);
insert into COMPUTERS values (115,'Cray T3E', 0);
insert into COMPUTERS values (116,'Cray C90', 0);
insert into COMPUTERS values (117,'Cray T90', 0);
insert into COMPUTERS values (118,'Cray SV1', 0);
insert into COMPUTERS values (119,'Cray J90', 0);
insert into COMPUTERS values (120,'Cray XT3', 0);
insert into COMPUTERS values (121,'Cray CS6400', 0);
insert into COMPUTERS values (122,'Atari ST', 20);
insert into COMPUTERS values (123,'Amiga 2500', 0);
insert into COMPUTERS values (124,'Amiga 2500', 6);
insert into COMPUTERS values (125,'Amiga 4000', 6);
insert into COMPUTERS values (126,'Amiga 3000UX', 6);
insert into COMPUTERS values (127,'Amiga 3000T', 6);
insert into COMPUTERS values (128,'Amiga 4000T', 6);
insert into COMPUTERS values (129,'Amiga 1200', 6);
insert into COMPUTERS values (130,'Atari 1040 STf', 0);
insert into COMPUTERS values (131,'Atari 520 ST', 0);
insert into COMPUTERS values (132,'Atari 520 STfm', 0);
insert into COMPUTERS values (133,'Atari 1040 STe', 0);
insert into COMPUTERS values (134,'Atari MEGA STe', 0);
insert into COMPUTERS values (135,'Atari 520 ST+', 0);
insert into COMPUTERS values (136,'Atari 520 STm', 0);
insert into COMPUTERS values (137,'Atari 130 ST', 0);
insert into COMPUTERS values (138,'Atari 260 ST', 0);
insert into COMPUTERS values (139,'Atari MEGA ST', 0);
insert into COMPUTERS values (140,'Atari 520 STf', 0);
insert into COMPUTERS values (141,'Atari 1040 STfm', 0);
insert into COMPUTERS values (142,'Atari 2080 ST', 0);
insert into COMPUTERS values (143,'Atari 260 ST+', 0);
insert into COMPUTERS values (144,'Atari 4160 STe', 0);
insert into COMPUTERS values (145,'TRS-80 Color ComputersRow 2', 0);
insert into COMPUTERS values (146,'TRS-80 Color ComputersRow 3', 0);
insert into COMPUTERS values (147,'TRS-80 Model 1', 5);
insert into COMPUTERS values (148,'Timex Sinclair 2068', 23);
insert into COMPUTERS values (149,'ZX Spectrum', 25);
insert into COMPUTERS values (150,'Xerox Star', 26);
insert into COMPUTERS values (151,'Xerox Alto', 0);
insert into COMPUTERS values (152,'Acorn Archimedes', 22);
insert into COMPUTERS values (153,'Nintendo Entertainment System', 24);
insert into COMPUTERS values (154,'Super Nintendo Entertainment System', 24);
insert into COMPUTERS values (155,'Super Famicom', 0);
insert into COMPUTERS values (156,'Nintendo GameCube', 24);
insert into COMPUTERS values (157,'Game Boy line', 0);
insert into COMPUTERS values (158,'PlayStation', 17);
insert into COMPUTERS values (159,'PlayStation 2', 17);
insert into COMPUTERS values (160,'Game & Watch', 24);
insert into COMPUTERS values (161,'EDSAC', 0);
insert into COMPUTERS values (162,'IBM System/4 Pi', 0);
insert into COMPUTERS values (163,'IBM AP-101', 0);
insert into COMPUTERS values (164,'IBM TC-1', 0);
insert into COMPUTERS values (165,'IBM AP-101B', 0);
insert into COMPUTERS values (166,'IBM AP-101S', 13);
insert into COMPUTERS values (167,'ProLiant', 27);
insert into COMPUTERS values (168,'Http://nepomuk.semanticdesktop.org/xwiki/', 0);
insert into COMPUTERS values (169,'Sinclair QL', 25);
insert into COMPUTERS values (170,'Sinclair ZX81', 25);
insert into COMPUTERS values (171,'Sinclair ZX80', 25);
insert into COMPUTERS values (172,'Atari 65XE', 20);
insert into COMPUTERS values (173,'Deep Blue', 0);
insert into COMPUTERS values (174,'Macintosh Quadra 650', 0);
insert into COMPUTERS values (175,'Macintosh Quadra 610', 0);
insert into COMPUTERS values (176,'Macintosh Quadra 800', 0);
insert into COMPUTERS values (177,'Macintosh Quadra 950', 0);
insert into COMPUTERS values (178,'PowerBook 160', 0);
insert into COMPUTERS values (179,'PowerBook 145B', 0);
insert into COMPUTERS values (180,'PowerBook 170', 0);
insert into COMPUTERS values (181,'PowerBook 145', 0);
insert into COMPUTERS values (182,'PowerBook G3', 0);
insert into COMPUTERS values (183,'PowerBook 140', 0);
insert into COMPUTERS values (184,'Macintosh IIcx', 0);
insert into COMPUTERS values (185,'Powerbook 180', 0);
insert into COMPUTERS values (186,'PowerBook G4', 0);
insert into COMPUTERS values (187,'Macintosh XL', 0);
insert into COMPUTERS values (188,'PowerBook 100', 0);
insert into COMPUTERS values (189,'PowerBook 2400c', 0);
insert into COMPUTERS values (190,'PowerBook 1400', 0);
insert into COMPUTERS values (191,'Macintosh Quadra 630', 0);
insert into COMPUTERS values (192,'Macintosh Quadra 660AV', 0);
insert into COMPUTERS values (193,'Macintosh Quadra 840AV', 0);
insert into COMPUTERS values (194,'PowerBook 5300', 0);
insert into COMPUTERS values (195,'PowerBook 3400c', 0);
insert into COMPUTERS values (196,'Macintosh Color Classic', 0);
insert into COMPUTERS values (197,'Macintosh 512Ke', 0);
insert into COMPUTERS values (198,'Macintosh IIsi', 0);
insert into COMPUTERS values (199,'Macintosh IIx', 0);
insert into COMPUTERS values (200,'PowerBook 500 series', 0);
insert into COMPUTERS values (201,'Power Macintosh G3', 0);
insert into COMPUTERS values (202,'Macintosh IIci', 0);
insert into COMPUTERS values (203,'iMac G5', 1);
insert into COMPUTERS values (204,'Power Mac G4', 0);
insert into COMPUTERS values (205,'Power Macintosh 7100', 0);
insert into COMPUTERS values (206,'Power Macintosh 9600', 0);
insert into COMPUTERS values (207,'Power Macintosh 7200', 0);
insert into COMPUTERS values (208,'Power Macintosh 7300', 0);
insert into COMPUTERS values (209,'Power Macintosh 8600', 0);
insert into COMPUTERS values (210,'Power Macintosh 6200', 0);
insert into COMPUTERS values (211,'Power Macintosh 8100', 0);
insert into COMPUTERS values (212,'Compact Macintosh', 0);
insert into COMPUTERS values (213,'Power Macintosh 4400', 0);
insert into COMPUTERS values (214,'Power Macintosh 9500', 0);
insert into COMPUTERS values (215,'Macintosh Portable', 0);
insert into COMPUTERS values (216,'EMac', 0);
insert into COMPUTERS values (217,'Power Macintosh 7600', 0);
insert into COMPUTERS values (218,'Power Mac G5', 0);
insert into COMPUTERS values (219,'Power Macintosh 7500', 0);
insert into COMPUTERS values (220,'Power Macintosh 6100', 0);
insert into COMPUTERS values (221,'Power Macintosh 8500', 0);
insert into COMPUTERS values (222,'Macintosh IIvi', 0);
insert into COMPUTERS values (223,'Macintosh IIvx', 0);
insert into COMPUTERS values (224,'IMac G3', 0);
insert into COMPUTERS values (225,'IMac G4', 0);
insert into COMPUTERS values (226,'Power Mac G4 Cube', 1);
insert into COMPUTERS values (227,'Intel iMac', 0);
insert into COMPUTERS values (228,'Deep Thought', 13);
insert into COMPUTERS values (229,'Wii', 24);
insert into COMPUTERS values (230,'IBM System x', 0);
insert into COMPUTERS values (231,'IBM System i', 13);
insert into COMPUTERS values (232,'IBM System z', 13);
insert into COMPUTERS values (233,'IBM System p', 13);
insert into COMPUTERS values (234,'LC 575', 0);
insert into COMPUTERS values (235,'Macintosh TV', 0);
insert into COMPUTERS values (236,'Macintosh Performa', 0);
insert into COMPUTERS values (237,'Macintosh II series', 0);
insert into COMPUTERS values (238,'Power Macintosh 6400', 0);
insert into COMPUTERS values (239,'Power Macintosh 6500', 0);
insert into COMPUTERS values (240,'Apple PenLite', 0);
insert into COMPUTERS values (241,'Wallstreet', 0);
insert into COMPUTERS values (242,'Twentieth Anniversary Macintosh', 0);
insert into COMPUTERS values (243,'Power Macintosh 5500', 0);
insert into COMPUTERS values (244,'iBook G3', 1);
insert into COMPUTERS values (245,'Power Macintosh 5200 LC', 0);
insert into COMPUTERS values (246,'Power Macintosh 5400', 0);
insert into COMPUTERS values (247,'CM-1', 0);
insert into COMPUTERS values (248,'MSX', 28);
insert into COMPUTERS values (249,'PlayStation 3', 17);
insert into COMPUTERS values (250,'MSX2', 29);
insert into COMPUTERS values (251,'MSX2+', 30);
insert into COMPUTERS values (252,'MSX turbo R', 0);
insert into COMPUTERS values (253,'Panasonic FS A1GT', 0);
insert into COMPUTERS values (254,'Panasonic FS A1ST', 0);
insert into COMPUTERS values (255,'PDP-11', 10);
insert into COMPUTERS values (256,'PDP-1', 10);
insert into COMPUTERS values (257,'PDP-10', 10);
insert into COMPUTERS values (258,'PDP-8', 10);
insert into COMPUTERS values (259,'PDP-6', 10);
insert into COMPUTERS values (260,'DECSYSTEM-20', 10);
insert into COMPUTERS values (261,'PDP-7', 10);
insert into COMPUTERS values (262,'PDP-5', 10);
insert into COMPUTERS values (263,'PDP-12', 10);
insert into COMPUTERS values (264,'LINC', 10);
insert into COMPUTERS values (265,'PDP-14', 10);
insert into COMPUTERS values (266,'PDP-15', 10);
insert into COMPUTERS values (267,'PDP-16', 10);
insert into COMPUTERS values (268,'Cray X2', 31);
insert into COMPUTERS values (269,'Cray X-MP', 31);
insert into COMPUTERS values (270,'Evans & Sutherland ES-1', 32);
insert into COMPUTERS values (271,'Commodore VIC-20', 6);
insert into COMPUTERS values (272,'PowerBook 150', 0);
insert into COMPUTERS values (273,'MacBook Air', 1);
insert into COMPUTERS values (274,'Digi-Comp I', 33);
insert into COMPUTERS values (275,'Digi-Comp', 0);
insert into COMPUTERS values (276,'Digi-Comp II', 33);
insert into COMPUTERS values (277,'Manchester Mark I', 0);
insert into COMPUTERS values (278,'Small-Scale Experimental Machine', 0);
insert into COMPUTERS values (279,'Nintendo 64', 24);
insert into COMPUTERS values (280,'Game Boy Advance', 24);
insert into COMPUTERS values (281,'Game Boy', 24);
insert into COMPUTERS values (282,'Nintendo DS Lite', 24);
insert into COMPUTERS values (283,'Nintendo DS', 24);
insert into COMPUTERS values (284,'Game Boy Color', 24);
insert into COMPUTERS values (285,'Game Boy Advance SP', 24);
insert into COMPUTERS values (286,'Virtual Boy', 24);
insert into COMPUTERS values (287,'Game Boy Micro', 24);
insert into COMPUTERS values (288,'Roadrunner', 13);
insert into COMPUTERS values (289,'HP 9000', 0);
insert into COMPUTERS values (290,'OMRON Luna-88K2', 0);
insert into COMPUTERS values (291,'OMRON Luna-88K', 34);
insert into COMPUTERS values (292,'Motorola series 900', 0);
insert into COMPUTERS values (293,'Motorola M8120', 0);
insert into COMPUTERS values (294,'Triton Dolphin System 100', 0);
insert into COMPUTERS values (295,'BBN TC2000', 35);
insert into COMPUTERS values (296,'WRT54G', 0);
insert into COMPUTERS values (297,'ThinkPad', 36);
insert into COMPUTERS values (298,'Apple Newton', 1);
insert into COMPUTERS values (299,'Atanasoff-Berry ComputersRow', 0);
insert into COMPUTERS values (300,'Atlas ComputersRow', 0);
insert into COMPUTERS values (301,'ASUS Eee PC 901', 37);
insert into COMPUTERS values (302,'ASUS Eee PC 701', 0);
insert into COMPUTERS values (303,'IBM 7030', 13);
insert into COMPUTERS values (304,'System/38', 13);
insert into COMPUTERS values (305,'System/36', 13);
insert into COMPUTERS values (306,'IBM 7090', 13);
insert into COMPUTERS values (307,'IBM RT', 13);
insert into COMPUTERS values (308,'System/360', 13);
insert into COMPUTERS values (309,'IBM 801', 13);
insert into COMPUTERS values (310,'IBM 1401', 13);
insert into COMPUTERS values (311,'ASCI White', 13);
insert into COMPUTERS values (312,'Blue Gene', 13);
insert into COMPUTERS values (313,'ASCI Blue Pacific', 13);
insert into COMPUTERS values (314,'iPhone', 1);
insert into COMPUTERS values (315,'Nokia N810', 16);
insert into COMPUTERS values (316,'EDSAC 2', 0);
insert into COMPUTERS values (317,'Titan', 0);
insert into COMPUTERS values (318,'Pilot ACE', 0);
insert into COMPUTERS values (319,'HP Mini 1000', 27);
insert into COMPUTERS values (320,'HP 2133 Mini-Note PC', 27);
insert into COMPUTERS values (321,'Kogan Agora Pro', 0);
insert into COMPUTERS values (322,'D-Series Machines', 0);
insert into COMPUTERS values (323,'ZX Spectrum 48K', 25);
insert into COMPUTERS values (324,'ZX Spectrum 16K', 25);
insert into COMPUTERS values (325,'ZX Spectrum 128', 25);
insert into COMPUTERS values (326,'ZX Spectrum +3', 38);
insert into COMPUTERS values (327,'ZX Spectrum +2', 38);
insert into COMPUTERS values (328,'ZX Spectrum +2A', 38);
insert into COMPUTERS values (329,'ZX Spectrum +', 25);
insert into COMPUTERS values (330,'Acer Extensa', 0);
insert into COMPUTERS values (331,'Acer Extensa 5220', 0);
insert into COMPUTERS values (332,'Dell Latitude', 0);
insert into COMPUTERS values (333,'Toshiba Satellite', 0);
insert into COMPUTERS values (334,'Timex Sinclair 2048', 23);
insert into COMPUTERS values (335,'Sprinter', 0);
insert into COMPUTERS values (336,'Timex ComputersRow 2048', 0);
insert into COMPUTERS values (337,'Pentagon', 0);
insert into COMPUTERS values (338,'Belle', 0);
insert into COMPUTERS values (339,'Loki', 25);
insert into COMPUTERS values (340,'Hobbit', 0);
insert into COMPUTERS values (341,'NeXT ComputersRow', 19);
insert into COMPUTERS values (342,'TRS-80', 0);
insert into COMPUTERS values (343,'TRS-80 Model 2', 5);
insert into COMPUTERS values (344,'TRS-80 Model 3', 5);
insert into COMPUTERS values (345,'STacy', 0);
insert into COMPUTERS values (346,'ST BOOK', 0);
insert into COMPUTERS values (347,'Atari 520 STE', 0);
insert into COMPUTERS values (348,'Amiga 2000 Model A', 0);
insert into COMPUTERS values (349,'Amiga 2000 Model B', 0);
insert into COMPUTERS values (350,'Amiga 2000 Model C', 0);
insert into COMPUTERS values (351,'IBM 3270', 0);
insert into COMPUTERS values (352,'CALDIC', 0);
insert into COMPUTERS values (353,'Modbook', 0);
insert into COMPUTERS values (354,'Compaq SystemPro', 0);
insert into COMPUTERS values (355,'ARRA', 0);
insert into COMPUTERS values (356,'IBM System Cluster 1350', 0);
insert into COMPUTERS values (357,'Finite element machine', 0);
insert into COMPUTERS values (358,'ES7000', 0);
insert into COMPUTERS values (359,'HP MediaSmart Server', 0);
insert into COMPUTERS values (360,'HP Superdome', 0);
insert into COMPUTERS values (361,'IBM Power Systems', 13);
insert into COMPUTERS values (362,'Oslo Analyzer', 0);
insert into COMPUTERS values (363,'Microsoft Softcard', 0);
insert into COMPUTERS values (364,'WITCH', 0);
insert into COMPUTERS values (365,'Analytical engine', 0);
insert into COMPUTERS values (366,'EDVAC', 0);
insert into COMPUTERS values (367,'BINAC', 0);
insert into COMPUTERS values (368,'Earth Simulator', 0);
insert into COMPUTERS values (369,'BARK', 0);
insert into COMPUTERS values (370,'Harvard Mark I', 13);
insert into COMPUTERS values (371,'ILLIAC IV', 0);
insert into COMPUTERS values (372,'ILLIAC II', 0);
insert into COMPUTERS values (373,'ILLIAC III', 0);
insert into COMPUTERS values (374,'Water integrator', 0);
insert into COMPUTERS values (375,'CSIRAC', 0);
insert into COMPUTERS values (376,'System X', 0);
insert into COMPUTERS values (377,'Harvest', 0);
insert into COMPUTERS values (378,'ChipTest', 0);
insert into COMPUTERS values (379,'HiTech', 0);
insert into COMPUTERS values (380,'Bomba', 0);
insert into COMPUTERS values (381,'ACE', 0);
insert into COMPUTERS values (382,'ASCI Red', 0);
insert into COMPUTERS values (383,'ASCI Thors Hammer', 0);
insert into COMPUTERS values (384,'ASCI Purple', 13);
insert into COMPUTERS values (385,'ASCI Blue Mountain', 0);
insert into COMPUTERS values (386,'Columbia', 0);
insert into COMPUTERS values (387,'HP Integrity', 0);
insert into COMPUTERS values (388,'APEXC', 0);
insert into COMPUTERS values (389,'Datasaab D2', 0);
insert into COMPUTERS values (390,'BRLESC', 0);
insert into COMPUTERS values (391,'DYSEAC', 0);
insert into COMPUTERS values (392,'SSEC', 13);
insert into COMPUTERS values (393,'Hydra', 0);
insert into COMPUTERS values (394,'FUJIC', 0);
insert into COMPUTERS values (395,'RAYDAC', 0);
insert into COMPUTERS values (396,'Harvard Mark III', 0);
insert into COMPUTERS values (397,'DATAR', 0);
insert into COMPUTERS values (398,'ReserVec', 0);
insert into COMPUTERS values (399,'DASK', 0);
insert into COMPUTERS values (400,'UTEC', 0);
insert into COMPUTERS values (401,'DRTE ComputersRow', 0);
insert into COMPUTERS values (402,'PowerEdge', 0);
insert into COMPUTERS values (403,'Apple Network Server', 0);
insert into COMPUTERS values (404,'Goodyear MPP', 0);
insert into COMPUTERS values (405,'Macintosh 128K technical details', 0);
insert into COMPUTERS values (406,'Power Macintosh G3', 0);
insert into COMPUTERS values (407,'CER-10', 0);
insert into COMPUTERS values (408,'CER-20', 0);
insert into COMPUTERS values (409,'IBM BladeCenter', 13);
insert into COMPUTERS values (410,'Wisconsin Integrally Synchronized ComputersRow', 0);
insert into COMPUTERS values (411,'Amstrad CPC', 38);
insert into COMPUTERS values (412,'Amstrad CPC 6128', 38);
insert into COMPUTERS values (413,'Amstrad CPC 664', 38);
insert into COMPUTERS values (414,'Amstrad CPC 464', 38);
insert into COMPUTERS values (415,'Intergraph', 0);
insert into COMPUTERS values (416,'Enterprise', 0);
insert into COMPUTERS values (417,'MTX500', 0);
insert into COMPUTERS values (418,'Acorn Electron', 0);
insert into COMPUTERS values (419,'Sony Vaio P', 17);
insert into COMPUTERS values (420,'VAIO', 17);
insert into COMPUTERS values (421,'Sony Vaio P VGN-P588E/Q', 0);
insert into COMPUTERS values (422,'Sony Vaio P VGN-P530H/G', 0);
insert into COMPUTERS values (423,'Sony Vaio P VGN-P530H/W', 0);
insert into COMPUTERS values (424,'Sony Vaio P VGN-P530H/Q', 0);
insert into COMPUTERS values (425,'Sony Vaio P VGN-P530H/R', 0);
insert into COMPUTERS values (426,'Sony Vaio P VGN-P588E/R', 0);
insert into COMPUTERS values (427,'Sony Vaio P VGN-P598E/Q', 0);
insert into COMPUTERS values (428,'Timex Sinclair 1000', 23);
insert into COMPUTERS values (429,'Komputer 2086', 0);
insert into COMPUTERS values (430,'Galaksija', 0);
insert into COMPUTERS values (431,'Vector-06C', 0);
insert into COMPUTERS values (432,'Elektronika BK', 0);
insert into COMPUTERS values (433,'Sun386i', 39);
insert into COMPUTERS values (434,'Xerox Daybreak', 0);
insert into COMPUTERS values (435,'Xerox NoteTaker', 26);
insert into COMPUTERS values (436,'D4a', 0);
insert into COMPUTERS values (437,'LGP-30', 0);
insert into COMPUTERS values (438,'LGP-21', 0);
insert into COMPUTERS values (439,'ASUS Eee PC 900', 37);
insert into COMPUTERS values (440,'Atari TT030', 0);
insert into COMPUTERS values (441,'Bi Am ZX-Spectrum 48/64', 0);
insert into COMPUTERS values (442,'Bi Am ZX-Spectrum 128', 0);
insert into COMPUTERS values (443,'PlayStation Portable', 0);
insert into COMPUTERS values (444,'MSI Wind Netbook', 0);
insert into COMPUTERS values (445,'Sharp Mebius NJ70A', 0);
insert into COMPUTERS values (446,'HTC Snap', 41);
insert into COMPUTERS values (447,'Commodore Educator 64', 6);
insert into COMPUTERS values (448,'Amiga 1500', 6);
insert into COMPUTERS values (449,'Commodore 65', 6);
insert into COMPUTERS values (450,'Commodore 16', 6);
insert into COMPUTERS values (451,'Commodore CBM-II', 6);
insert into COMPUTERS values (452,'Commodore Plus/4', 6);
insert into COMPUTERS values (453,'Commodore LCD', 6);
insert into COMPUTERS values (454,'Commodore MAX Machine', 6);
insert into COMPUTERS values (455,'Aster CT-80', 0);
insert into COMPUTERS values (456,'Test', 0);
insert into COMPUTERS values (457,'MSI GX723', 0);
insert into COMPUTERS values (458,'Eee PC 1000HV', 0);
insert into COMPUTERS values (459,'VTech Laser 200', 0);
insert into COMPUTERS values (460,'CrunchPad', 0);
insert into COMPUTERS values (461,'Neo Geo', 0);
insert into COMPUTERS values (462,'Sega Mega Drive', 0);
insert into COMPUTERS values (463,'Sega Master System', 0);
insert into COMPUTERS values (464,'TurboGrafx-16', 0);
insert into COMPUTERS values (465,'Sun-3', 39);
insert into COMPUTERS values (466,'Pleiades', 0);
insert into COMPUTERS values (467,'IBM Sequoia', 0);
insert into COMPUTERS values (468,'Inves Spectrum 48k plus', 0);
insert into COMPUTERS values (469,'iPhone 3G', 0);
insert into COMPUTERS values (470,'iPhone 3GS', 0);
insert into COMPUTERS values (471,'Beagle Board', 40);
insert into COMPUTERS values (472,'HP nPar', 0);
insert into COMPUTERS values (473,'MacBook Family', 0);
insert into COMPUTERS values (474,'Reservisor', 0);
insert into COMPUTERS values (475,'BladeSystem', 0);
insert into COMPUTERS values (476,'lenovo thinkpad t60p', 0);
insert into COMPUTERS values (477,'lenovo thinkpad x200', 36);
insert into COMPUTERS values (478,'lenovo thinkpad t60', 0);
insert into COMPUTERS values (479,'lenovo thinkpad w700', 0);
insert into COMPUTERS values (480,'lenovo thinkpad t41', 0);
insert into COMPUTERS values (481,'lenovo thinkpad z61p', 0);
insert into COMPUTERS values (482,'lenovo thinkpad x61s', 0);
insert into COMPUTERS values (483,'lenovo thinkpad t43', 0);
insert into COMPUTERS values (484,'lenovo thinkpad r400', 0);
insert into COMPUTERS values (485,'lenovo thinkpad x60s', 0);
insert into COMPUTERS values (486,'lenovo thinkpad x301', 0);
insert into COMPUTERS values (487,'lenovo thinkpad t42', 0);
insert into COMPUTERS values (488,'lenovo thinkpad r61', 0);
insert into COMPUTERS values (489,'lenovo thinkpad w500', 0);
insert into COMPUTERS values (490,'lenovo thinkpad sl400', 0);
insert into COMPUTERS values (491,'lenovo thinkpad x40', 0);
insert into COMPUTERS values (492,'lenovo thinkpad x200 tablet', 36);
insert into COMPUTERS values (493,'lenovo thinkpad t400s', 0);
insert into COMPUTERS values (494,'Nokia N900', 16);
insert into COMPUTERS values (495,'Internet Tablet', 0);
insert into COMPUTERS values (496,'Meiko Computing Surface', 0);
insert into COMPUTERS values (497,'CS-2', 0);
insert into COMPUTERS values (498,'IBM 701', 13);
insert into COMPUTERS values (499,'IBM 5100', 13);
insert into COMPUTERS values (500,'AN/FSQ-7', 13);
insert into COMPUTERS values (501,'AN/FSQ-32', 13);
insert into COMPUTERS values (502,'IBM CPC', 13);
insert into COMPUTERS values (503,'System/34', 13);
insert into COMPUTERS values (504,'System/32', 13);
insert into COMPUTERS values (505,'System/3', 13);
insert into COMPUTERS values (506,'IBM 305', 13);
insert into COMPUTERS values (507,'English Electric DEUCE', 0);
insert into COMPUTERS values (508,'CER-203', 0);
insert into COMPUTERS values (509,'CER-22', 0);
insert into COMPUTERS values (510,'Kentucky Linux Athlon Testbed', 0);
insert into COMPUTERS values (511,'QNAP TS-101', 0);
insert into COMPUTERS values (512,'iPad', 1);
insert into COMPUTERS values (513,'iPhone 2G', 0);
insert into COMPUTERS values (514,'Inslaw', 0);
insert into COMPUTERS values (515,'WePad', 0);
insert into COMPUTERS values (516,'MacBook Parts', 1);
insert into COMPUTERS values (517,'MacBook 13-inch Core 2 Duo 2.13GHz (MC240LL/A) DDR2 Model', 1);
insert into COMPUTERS values (518,'MacBook 13-inch Core 2 Duo 2.13GHz (MC240T/A) DDR2 Model', 0);
insert into COMPUTERS values (519,'MacBook 13-inch Core 2 Duo 2.13GHz (MC240X/A) DDR2 Model', 0);
insert into COMPUTERS values (520,'MacBook 13-inch Core 2 Duo 2.26GHz (Unibody MC207LL/A) DDR3 Model', 0);
insert into COMPUTERS values (521,'MC240LL/A', 0);
insert into COMPUTERS values (522,'D.K.COMMUNICATION', 0);
insert into COMPUTERS values (523,'iPhone 4', 1);
insert into COMPUTERS values (524,'Nintendo 3DS', 24);
insert into COMPUTERS values (525,'ASUS Eee PC 1005PE', 37);
insert into COMPUTERS values (526,'National Law Enforcement System', 0);
insert into COMPUTERS values (527,'BlackBerry PlayBook', 42);
insert into COMPUTERS values (528,'Barnes & Noble nook', 0);
insert into COMPUTERS values (529,'SAM Coupé', 0);
insert into COMPUTERS values (530,'HTC Dream', 41);
insert into COMPUTERS values (531,'Samsung Galaxy Tab', 43);
insert into COMPUTERS values (532,'BlackBerry PlayBook', 42);
insert into COMPUTERS values (533,'Tianhe-I', 0);
insert into COMPUTERS values (534,'Kno', 0);
insert into COMPUTERS values (535,'ThinkPad 701 C', 0);
insert into COMPUTERS values (536,'ThinkPad 340 CSE', 0);
insert into COMPUTERS values (537,'ThinkPad 755 CX', 0);
insert into COMPUTERS values (538,'ThinkPad 755 CE', 0);
insert into COMPUTERS values (539,'ThinkPad 370 C', 0);
insert into COMPUTERS values (540,'Coleco Adam', 0);
insert into COMPUTERS values (541,'Nebulae', 0);
insert into COMPUTERS values (542,'Alex eReader', 0);
insert into COMPUTERS values (543,'Acer Iconia', 0);
insert into COMPUTERS values (544,'Archos 101', 0);
insert into COMPUTERS values (545,'Fujitsu Lifebook T900', 0);
insert into COMPUTERS values (546,'Motorola Xoom', 0);
insert into COMPUTERS values (547,'ViewSonic G Tablet', 0);
insert into COMPUTERS values (548,'DEC Professional', 10);
insert into COMPUTERS values (549,'DEC Multia', 10);
insert into COMPUTERS values (550,'DEC Firefly', 10);
insert into COMPUTERS values (551,'DEC 3000 AXP', 10);
insert into COMPUTERS values (552,'DEC 2000 AXP', 10);
insert into COMPUTERS values (553,'DEC 4000 AXP', 10);
insert into COMPUTERS values (554,'DEC 7000/10000 AXP', 10);
insert into COMPUTERS values (555,'DEC Professional 350', 0);
insert into COMPUTERS values (556,'DEC Rainbow 100', 0);
insert into COMPUTERS values (557,'DEC Professional 325', 0);
insert into COMPUTERS values (558,'DECmate II', 10);
insert into COMPUTERS values (559,'DECmate', 10);
insert into COMPUTERS values (560,'DECsystem', 10);
insert into COMPUTERS values (561,'NetApp Filer', 0);
insert into COMPUTERS values (562,'DEC GT40', 10);
insert into COMPUTERS values (563,'ecoATM', 0);
insert into COMPUTERS values (564,'MindWave BrainCubed Education Bundle', 0);
insert into COMPUTERS values (565,'PalmPilot', 0);
insert into COMPUTERS values (566,'Upcoming iPhone 5', 1);
insert into COMPUTERS values (567,'Dell Inspiron 560 Desktop ComputersRow ', 0);
insert into COMPUTERS values (568,'IPad 2', 1);
insert into COMPUTERS values (569,'HP TouchPad', 27);
insert into COMPUTERS values (570,'HP Veer', 27);
insert into COMPUTERS values (571,'Lenovo Thinkpad Edge 11', 36);
insert into COMPUTERS values (572,'Dell Vostro', 0);
insert into COMPUTERS values (573,'Gateway LT3103U', 0);
insert into COMPUTERS values (574,'iPhone 4S', 1);