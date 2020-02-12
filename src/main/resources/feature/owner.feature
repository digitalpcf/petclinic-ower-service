Feature: Veterinarian

  Background: Application has veterinarian in the database.

  Scenario: Search for complete veterinarian

	Given Jonn search for veterinarian available

	Then  John should get the veterinarian as
	  | firstName | lastName | Specialties |
	  | James     | Carter   | none        |
	  | Helen     | Leary    | radiology   |
	  | Linda     | Douglas  | surgery     |
	  | Rafael    | Ortega   | surgery     |
	  | Henry     | Stevens  | radiology   |
	  | Sharon    | Jenkins  |             |

