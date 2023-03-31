INSERT INTO Account (first_name, last_Name, username, password, phone_number, email, address, cardnumber) values ('Jonathan', 'Henriksson', 'Hej', '$2a$11$ebZxgeA7F2u//ra0.y6FAOO30a5IrDS99P1FnaFvwBTpy.poKOajS', '0705272958', 'Hej@email.com', 'Gymnasievägen 9', '55555555');
INSERT INTO Account (first_name, last_Name, username, password, phone_number, email, address, cardnumber) values ('Jonathan', 'Tägget', 'Jonte_H4xx0r', 'jonte', '356863221', 'Hej@email.com', 'Thäääägevägen 9', '46578954');
INSERT INTO Account (first_name, last_Name, username, password, phone_number, email, address, cardnumber) values ('Björn', 'Forceberk', 'bjorn', 'bjorn', '234567854', 'Fursten@email.com', 'Örjansvägen 9', '34567890');
INSERT INTO Account (first_name, last_Name, username, password, phone_number, email, address, cardnumber) values ('Lucas', 'Curry', 'lucas', 'lucas', '3457690432', 'curry@email.com', 'Kungsgatan 3', '235476843');
INSERT INTO Account (first_name, last_Name, username, password, phone_number, email, address, cardnumber) values ('First', 'Guy', '1', '1', '2345678678', 'Hej@email.com', 'Sturegatan 16', '24357982');
INSERT INTO Account (first_name, last_Name, username, password, phone_number, email, address, cardnumber) values ('Mahsa', 'Kamyab', 'Mahsa', 'Mahsa', '234576822', 'MKamyab@email.com', 'Persvägen 97', '3456832');

INSERT INTO Task (account_id, title, address, price, image, description, city, created_at) values (1, 'Sophämtning', 'Börjegatan 123', 300, 'https://www.hem.se/contentassets/40cd4a5bddf14b869cc91d4db145b748/soppasar_rityta_12.png?width=800', 'Sopberg', 'Stockholm', '2023-03-28 14:00:00');
INSERT INTO Task (account_id, title, address, price, image, description, city, created_at) values (2, 'Fryshämtning', 'Örjansvägen 13', 2300, 'https://www.elgiganten.se/image/dv_web_D180001002200412/15554/matsui-frys-muf48w19e--pdp_zoom-3000--pdp_main-960.jpg', 'HJÄLP!', 'Göteborg', '2023-01-24 11:00:00');
INSERT INTO Task (account_id, title, address, price, image, description, city, created_at) values (3, 'Gammal dator', 'Kungsgatan 1337', 270, 'https://www.elgiganten.se/image/dv_web_D1800010021076902/460086/asus-x415-i7-108512-14-barbar-dator--pdp_zoom-3000--pdp_main-960.jpg', 'Trasigt äpple', 'Göteborg','2023-02-28 12:02:00');
INSERT INTO Task (account_id, title, address, price, image, description, city, created_at) values (4, 'Lövsäckar', 'Sturevägen 12', 150, 'https://www.harald-nyborg.se/media/amasty/webp/catalog/product/cache/2576f790736261dc1871307d4a4d6721/5/7/57760a5ebc0001f579a7a6c5659c31374b653911a5edc56a2b6244f8692408e4_jpeg.webp', 'Kan någon hjälpa mig?', 'Malmö', '2023-03-29 15:00:00');
INSERT INTO Task (account_id, title, address, price, image, description, city, created_at) values (2, 'Utrivet kök', 'Sanatoriegatan 32b', 1500, 'https://s.tiptapp.co/images/ads/178ad5ddabc44856-1617816313032', 'Har rivit ut mitt gamla kök och behöver hjälp att köra det till tippen.', 'Göteborg', '2023-03-29 15:00:00');
INSERT INTO Task (account_id, title, address, price, image, description, city, created_at) values (1, 'Kvistar', 'Landerigatan 44', 360, 'https://s.tiptapp.co/images/ads/17164955d4346987-1586530452687-0', 'Trimmat träden och behöver dessa bortforslade till ÅVC.', 'Göteborg','2023-03-26 22:34:00');
INSERT INTO Task (account_id, title, address, price, image, description, city, created_at) values (3, 'Julgranar', 'Danskavägen 18', 150, 'https://8sidor.se/wp-content/uploads/2023/01/granar.jpg', 'Hej! Frugan tvingar mig att slänga min samling av gamla granar...', 'Göteborg','2023-03-25 18:40:00');
INSERT INTO Task (account_id, title, address, price, image, description, city, created_at) values (1, 'Eka', 'Drottningsgatan 4', 350, 'http://www.kalimera.se/bilder/forum/brygga_07.jpg', 'Har en trasig gammal eka som jag behöver hjälp med att köra till ÅVC.', 'Malmö','2023-03-30 15:56:00');
INSERT INTO Task (account_id, title, address, price, image, description, city, created_at) values (5, 'Rensning i bakgården', 'Virginsgatan 28', 600, 'https://media.istockphoto.com/id/1134921236/sv/foto/soptipp-p%C3%A5-bakg%C3%A5rden-av-huset.jpg?s=170667a&w=0&k=20&c=nC_6RLbN9VMUP63STSGJaYJJjJQ0FkRp3wtjKlykp-U=', 'Har lite bös i bakgården som jag tänkte bli av med! ', 'Göteborg', '2023-03-24 13:00:00');
INSERT INTO Task (account_id, title, address, price, image, description, city, created_at) values (6, 'Fallfrukt', 'Bergagatan 54', 450, 'https://images.freeimages.com/images/large-previews/629/rotten-apples-1173776.jpg', 'Behöver någon som kan frakta bort en massa fallfrukt åt mig till tippen', 'Stockholm', '2023-03-12 07:30:00');