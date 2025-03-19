// Tách cột "date" và "amount"
let labels3 = df_group_month.map(item => item.month);  // Danh sách ngày
let amounts3 = df_group_month.map(item => item.amount);  // Danh sách số tiền

// Cấu hình biểu đồ
let expense = document.getElementById("spendingChart").getContext("2d");

const stackedBar = new Chart(expense, {
    type: 'bar',
    data: {
        labels: labels3,
        datasets: [{
            label: "Amount (đ)",
            data: amounts3,
            borderColor: "blue",
            backgroundColor: "rgba(0, 0, 255, 0.2)",
            borderWidth: 2
        }]
    },
    options: {
        scales: {
            x: {
                stacked: true
            },
            y: {
                stacked: true
            }
        }
    }
});