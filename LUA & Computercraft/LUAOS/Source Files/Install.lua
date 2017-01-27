--This program will install all the required components under the right names, please do not edit the names or delete any of the programs--
local name = "test "
local version = "1.0.0 "
print("Do you want to install " .. name .. version .. "(y/n)")
local startDownload = false
local overwrite = false
local directory = "LUAOS"
local function consoleLog(logType, message)
	local TIME = textutils.formatTime(os.time(), false)
	local head = "[" .. TIME .. "] " .. "[".. logType .. "]"
	print(head .. " " .. message)
end
local function download(file, name)
	shell.run("cd LUAOS")
	if fs.exists("luaos/"..name) and not overwrite then
		print(name.. " already exists and is needed for the program to run, would you like to overwrite it?")
		print("1. Yes")
		print("2. Yes to all")
		print("3. No")
		local selection = read()
		if selection == "1" then
			shell.run("delete " .. name)
			shell.run("pastebin get " .. file .. " " .. name)
		elseif selection == "2" then
			shell.run("delete " .. name)
			shell.run("pastebin get " .. file .. " " .. name)
			overwrite = true
		end
	elseif fs.exists(name) and overwrite then
		shell.run("delete " .. name)
		shell.run("pastebin get " .. file .. " " .. name)
	else
		shell.run("pastebin get " .. file .. " " .. name)
	end
	shell.run("cd ..")
end
local function install()
	shell.run("mkdir LUAOS")
	--Needed Components--
	--Main 			pHd6Fjn7
	--Boot			LL7R16BT
	--Desktop		1kVa6KNx
	--Formatting	mxR0mpVj
	term.setCursorPos(1,1)
	term.clear()
	consoleLog("INFO", "Starting Install...")
	os.sleep(1)
	--Downloading required components--
	consoleLog("INFO", "Downloading Main.lua...")
	download("pHd6Fjn7", "Main")
	consoleLog("INFO", "Downloading Startup.lua...")
	download("LL7R16BT","Boot")
	consoleLog("INFO", "Downloading Desktop.lua...")
	download("1kVa6KNx","Desktop")
	consoleLog("INFO", "Downloading Formatting.lua...")
	download("mxR0mpVj","Formatting")
	print("Install Fineshed, run \" LUAOS\" to start the OS ")
	shell.run("pastebin get acEpM1tP Run")
end

if read() == "y" then
	startDownload = true
else
	startDownload = false
end
if startDownload then
	install()
end