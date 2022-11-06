/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  guilhermevillaca
 * Created: Nov 6, 2022
 */

CREATE TABLE filme(
  id INT NOT NULL,
  "year" VARCHAR(4) NOT NULL,
  title VARCHAR(100) NOT NULL,
  studio VARCHAR(100) NOT NULL,
  producers VARCHAR(100) NOT NULL,
  winner VARCHAR(100)
);