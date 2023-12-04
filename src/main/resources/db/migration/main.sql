create database if not exists cnw;
use cnw;

CREATE TABLE `address` (
  `Id` int(11) NOT NULL,
  `Address` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `admin` (
  `Id` int(11) NOT NULL,
  `Username` varchar(255) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `admin`
--

INSERT INTO `admin` (`Id`, `Username`, `Password`) VALUES
(1, 'admin', 'pass');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `instuctor`
--

CREATE TABLE `instuctor` (
  `Id` int(11) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `Age` int(11) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `Phone` varchar(255) NOT NULL,
  `IdAddress` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tour`
--

CREATE TABLE `tour` (
  `Id` int(11) NOT NULL,
  `IdTraveler` int(11) NOT NULL,
  `IdInstuctor` int(11) NOT NULL,
  `IdAddress` int(11) NOT NULL,
  `Price` int(11) NOT NULL,
  `TotalTime` int(11) NOT NULL,
  `TimeStart` date NOT NULL,
  `TimeEnd` date NOT NULL,
  `Status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tralver_tour`
--

CREATE TABLE `tralver_tour` (
  `Id` int(11) NOT NULL,
  `IdTour` int(11) NOT NULL,
  `IdTraveler` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `traveler`
--

CREATE TABLE `traveler` (
  `Id` int(11) NOT NULL,
  `Username` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `Address` varchar(255) NOT NULL,
  `DayBorn` date NOT NULL,
  `Email` varchar(255) NOT NULL,
  `Phone` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`Id`);

--
-- Chỉ mục cho bảng `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`Id`);

--
-- Chỉ mục cho bảng `instuctor`
--
ALTER TABLE `instuctor`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `IdAddress` (`IdAddress`);

--
-- Chỉ mục cho bảng `tour`
--
ALTER TABLE `tour`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `IdAddress` (`IdAddress`),
  ADD KEY `IdInstuctor` (`IdInstuctor`);

--
-- Chỉ mục cho bảng `tralver_tour`
--
ALTER TABLE `tralver_tour`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `IdTraveler` (`IdTraveler`),
  ADD KEY `IdTour` (`IdTour`);

--
-- Chỉ mục cho bảng `traveler`
--
ALTER TABLE `traveler`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `admin`
--
ALTER TABLE `admin`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `instuctor`
--
ALTER TABLE `instuctor`
  ADD CONSTRAINT `instuctor_ibfk_1` FOREIGN KEY (`IdAddress`) REFERENCES `address` (`Id`);

--
-- Các ràng buộc cho bảng `tour`
--
ALTER TABLE `tour`
  ADD CONSTRAINT `tour_ibfk_1` FOREIGN KEY (`IdAddress`) REFERENCES `address` (`Id`),
  ADD CONSTRAINT `tour_ibfk_2` FOREIGN KEY (`IdInstuctor`) REFERENCES `instuctor` (`Id`);

--
-- Các ràng buộc cho bảng `tralver_tour`
--
ALTER TABLE `tralver_tour`
  ADD CONSTRAINT `tralver_tour_ibfk_1` FOREIGN KEY (`IdTraveler`) REFERENCES `traveler` (`Id`),
  ADD CONSTRAINT `tralver_tour_ibfk_2` FOREIGN KEY (`IdTour`) REFERENCES `tour` (`Id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
