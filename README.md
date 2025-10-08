# 🛒 PrestaShop E2E Automation Project

## 🔍 Overview
An **End-to-End Automation Testing Project** for [PrestaShop Demo](https://demo.prestashop.com/) using **Selenium, TestNG, and the Page Object Model (POM)**.  
The goal is to automate the full user flow — from accessing the demo site, creating an account, searching for a product, adding it to the cart, and verifying it was successfully added.

---

## ⚙️ Tech Stack

| Tool / Library | Purpose |
|----------------|----------|
| ☕ **Java (JDK 22)** | Programming language |
| 🧠 **Selenium WebDriver** | Web automation |
| 🧩 **TestNG** | Test management & assertions |
| 🧱 **POM (Page Object Model)** | Clean, maintainable structure |
| 📊 **Extent Reports** | Detailed HTML reporting |
| 📸 **Helper (Screenshots)** | Capture screenshots on failure |
| 🎭 **Java Faker** | Generate realistic random data |

---

## 📁 Project Structure

```text
src
┣ main
┃ ┗ java
┃   ┗ pages
┃       ┣ elementActions
┃       ┃   ┗ ElementActions.java
┃       ┣ CartPage.java
┃       ┣ HomePage.java
┃       ┣ ProductPage.java
┃       ┣ RegisterPage.java
┃       ┗ SearchResultsPage.java
┣ test
┃ ┗ java
┃     ┗ tests
┃         ┣ BaseTest.java
┃         ┣ E2E_PrestaShopTest.java
┃         ┗ HelperClass.java
Reports/
pom.xml
README.md
```




---

## 🧪 E2E Test Flow
1. Open PrestaShop demo site
2. Handle and navigate inside the cross-origin iframe
3. Register a new user using **Java Faker** data
4. Search for a product (e.g., “notebook”)
5. Verify that the product image is displayed
6. Add the product to the cart
7. Open the cart and assert the product was added successfully

Each step is logged in **Extent Report** using:  
`reporter("pass" | "fail" | "warning" | "info", "message");`

---

## ⚡ Key Highlights
- Smart handling of **cross-origin iframes**
- Custom **ElementActions** for reusable waits and actions
- Managed **complex layers** with JS-based clicks
- Used **form.submit()** for dynamic elements
- Clean and modular **POM structure** for easy maintenance

---

## 🧠 How to Run

```bash
# Clone the repo
git clone https://github.com/AhmedSayed28/AhmedSayed28-VIAVI-Solutions---Auto---Task.git

# Navigate to project folder
cd PrestaShop-Automation

# Run tests
mvn clean test
```


---

## ✨ Notes

This task was one of the most enjoyable and challenging ones I’ve done.  
It involved solving real-world automation issues and applying best practices throughout.  
A great exercise in patience, debugging, and clean code. 💪

---

## 👨‍💻 Author

**Ahmed Sayed**  
_Senior Software Test Engineer_  
📍 Cairo, Egypt  
🔗 [LinkedIn](https://www.linkedin.com/in/ahmed-sayed-a2039821a/)  
