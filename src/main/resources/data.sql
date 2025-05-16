INSERT INTO client (id, name, address, phone, password, state, identification, gender, age)
VALUES
  (gen_random_uuid(), 'Jose Lema', 'Otavalo sn y principal', '098254785', '1234', TRUE, '1111111111', 'M', 30),
  (gen_random_uuid(), 'Marianela Montalvo', 'Amazonas y NNUU', '097548965', '5678', TRUE, '2222222222', 'F', 28),
  (gen_random_uuid(), 'Juan Osorio', '13 junio y Equinoccial', '098874587', '1245', TRUE, '3333333333', 'M', 35);


INSERT INTO account (number, type, balance, state, client)
VALUES
  (478758, 'Ahorro', 2000, TRUE, (SELECT id FROM client WHERE identification = '1111111111')),
  (225487, 'Corriente', 100, TRUE, (SELECT id FROM client WHERE identification = '2222222222')),
  (495878, 'Ahorro', 0, TRUE, (SELECT id FROM client WHERE identification = '3333333333')),
  (496825, 'Ahorro', 540, TRUE, (SELECT id FROM client WHERE identification = '2222222222'));


INSERT INTO movement (id, type, amount, balance, account)
VALUES
  (gen_random_uuid(), 'Debito', 575, 2000, 478758),
  (gen_random_uuid(), 'Credito', 600, 100, 225487),
  (gen_random_uuid(), 'Credito', 150, 0, 495878),
  (gen_random_uuid(), 'Debito', 540, 540, 496825);